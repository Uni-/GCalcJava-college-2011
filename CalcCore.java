
public class CalcCore {
	/* the last saved buffer and current buffer */
	public static double pbuf;
	public static String buf;
	/* input process mode: append (true) or new (false) */
	public static boolean append;
	/* binary operator stack, depth 1; empty (null) or full (not null) */
	public static CalcOp.CallbackBinary callbackBinaryReady;
	/* handling main frame to handle other types of event such as key input */
	public static CalcFrame frame;
	/* radix system, field of enum RadixSys / angle system, field of enum AngleSys */
	public static RadixSys radixSys;
	public static AngleSys angleSys;
	/* grouping radio buttons */
	public static javax.swing.ButtonGroup[] buttonGroup = new javax.swing.ButtonGroup[] {new javax.swing.ButtonGroup(), new javax.swing.ButtonGroup()};
	public static enum RadixSys {
		BINARY, OCTAL, DECIMAL, HEXADECIMAL;
		public int base() {
			switch(this) {
			case HEXADECIMAL: return 16;
			case DECIMAL: return 10;
			case OCTAL: return 8;
			case BINARY: return 2;
			default: return 1;
			}
		}
		public CalcOp.Type[] disabledTypes() {
			switch(this) {
			case HEXADECIMAL:
				return new CalcOp.Type[] {CalcOp.Type.OPERATOR_UNARY, CalcOp.Type.META_OPTION_ANGLESYS};
			case DECIMAL:
				return new CalcOp.Type[] {CalcOp.Type.DIGIT_HEXADECIMAL};
			case OCTAL:
				return new CalcOp.Type[] {CalcOp.Type.DIGIT_DECIMAL, CalcOp.Type.DIGIT_HEXADECIMAL, CalcOp.Type.OPERATOR_UNARY, CalcOp.Type.META_OPTION_ANGLESYS};
			case BINARY:
				return new CalcOp.Type[] {CalcOp.Type.DIGIT_OCTAL, CalcOp.Type.DIGIT_DECIMAL, CalcOp.Type.DIGIT_HEXADECIMAL, CalcOp.Type.OPERATOR_UNARY, CalcOp.Type.META_OPTION_ANGLESYS};
			default:
				return null;
			}
		}
	}
	public static enum AngleSys {
		DEGREE, RADIAN;
	}
	public static javax.swing.ButtonGroup buttonGroup(CalcOp.Type type) {
		switch(type) {
		case META_OPTION_RADIXSYS:
			return buttonGroup[0];
		case META_OPTION_ANGLESYS:
			return buttonGroup[1];
		default:
			return null;
		}
	}
	public static void push(CalcOp hostInstance) {
		/* a CalcOp instance is pushed onto CalcCore when it is clicked as a javax.swing.JComponent instance */
		switch (hostInstance.type().requiredArgNum()) {
		case 0: case -1:
			((CalcOp.CallbackNonary)hostInstance.callback()).operate();
			refreshDisplay();
			break;
		case 1:
			if (callbackBinaryReady != null && !append)
				break;
			flush();
			pbuf = parse(buf = dep(((CalcOp.CallbackUnary)hostInstance.callback()).operate(parse(buf))));
			refreshDisplay();
			break;
		case 2:
			if (callbackBinaryReady != null && !append)
				break;
			flush();
			pbuf = parse(buf);
			callbackBinaryReady = (CalcOp.CallbackBinary)hostInstance.callback();
			break;
		}
	}
	public static void put(String c) {
		/* operand digits is putted; normally from CallbackNonary.operate() */
		if (append) {
			if (!(c.matches(".") && buf.indexOf(".") != -1))
				buf = buf.concat(c);
		}
		else {
			if (c.matches("0*"))
				buf = "0";
			else {
				buf = c;
				append = true;
			}
		}
		refreshDisplay();
	}
	public static String dep(double d) {
		/* a double value is converted to a String instance */
		if (d % 1.0 == 0 || radixSys != RadixSys.DECIMAL)
			return integerToString((int)d, radixSys.base());
		else
			return String.valueOf(d);
		
	}
	public static double parse(String buf) {
		/* a String instance is converted to a double value */
		try {
			if (buf.indexOf(".") == -1) {
				return Integer.parseInt(buf, radixSys.base());
			}
			else {
				if (radixSys == RadixSys.DECIMAL)
					return Double.parseDouble(buf);
				else
					return Integer.parseInt(buf.substring(0, buf.indexOf(".")), radixSys.base());
			}
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	public static String restoreBuf() {
		/* buffer String is refactored */
		buf = dep(parse(buf));
		refreshDisplay();
		return buf;
	}
	public static String integerToString(int value, int base) {
		/* an integer value is converted to a String instance, with arbitrary base */
		boolean negative = value < 0;
		String result = "";
		char[] digits = "0123456789ABCDEF".toCharArray();
		if (value == 0)
			return "0";
		value *= negative ? -1 : 1;
		while (value != 0) {
			result = String.valueOf(digits[value % base]).concat(result);
			value /= base;
		}
		result = negative ? "-".concat(result) : result;
		return result;
	}
	public static void setRadixSys(RadixSys radixSys) {
		/* when radixSys is changed, also should the buffer be rewritten with new base */
		double cbuf = parse(buf);
		CalcCore.radixSys = radixSys;
		buf = dep(cbuf);
		restoreBuf();
		refreshOptions();
		append = false;
	}
	public static void setAngleSys(AngleSys angleSys) {
		CalcCore.angleSys = angleSys;
	}
	public static double syscAngle(double s) {
		switch(angleSys) {
		case RADIAN:
			return s;
		case DEGREE:
			return s / 180.0 * Math.PI;
		default:
			return 0;
		}
	}
	public synchronized static void flush() {
		/* when a binary operator is popped from the stack, also should the saved buffer be processed and input mode be changed */
		restoreBuf();
		if (callbackBinaryReady != null) {
			double cbuf = ((CalcOp.CallbackBinary)callbackBinaryReady).operate(pbuf, parse(buf));
			pbuf = cbuf;
			buf = dep(cbuf);
			restoreBuf();
			callbackBinaryReady = null;
		}
		append = false;
	}
	public static void init() {
		/* this method is called only once, right after the construction of a CalcFrame instance. */
		radixSys = RadixSys.DECIMAL;
		angleSys = AngleSys.RADIAN;
		((CalcOpRadioButton)CalcPreset.instances[30]).setSelected(true);
		((CalcOpRadioButton)CalcPreset.instances[34]).setSelected(true);
	}
	public static void reset() {
		/* the 'clear' action. */
		pbuf = 0;
		buf = "0";
		append = false;
		callbackBinaryReady = null;
	}
	public synchronized static void refreshOptions() {
		for (CalcOp i : CalcPreset.instances) {
			boolean b = true;
			for (CalcOp.Type e : radixSys.disabledTypes())
				if (e == i.type())
					b = false;
			((javax.swing.JComponent)i).setEnabled(b);
		}
	}
	public synchronized static void refreshDisplay() {
		frame.getDisplay().setText(buf);
	}
	public synchronized static void refresh() {
		refreshOptions();
		refreshDisplay();
	}
	public static void main(String[] args) {
		frame = new CalcFrame();
		init();
		reset();
		refresh();
		frame.repaint();
	}
}

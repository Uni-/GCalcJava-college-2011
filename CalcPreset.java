
public final class CalcPreset {
	/* the width, height, uniform margin, uniform padding of frame */
	public static final int frmWidth = 480;
	public static final int frmHeight = 400;
	public static final int layUGap = 10;
	public static final int laySGap = 5;
	/* height of display panel; other panels are calculated */ 
	public static final int panTextHeight = 60;
	/* padding of display panel */
	public static final int panTextPadding = 5;
	/* grid of 3 panels */
	public static final int panSrGridRows = 4;
	public static final int panSaGridRows = 2;
	public static final int panTGridRows = 1;
	public static final int panSrGridCols = 1;
	public static final int panSaGridCols = 1;
	public static final int panTGridCols = 1;
	/* width of operators & operands panel; other panels are calculated */
	public static final int panBWidth = 360;
	/* grid of operators & operands panel */
	public static final int panBGridRows = 6;
	public static final int panBGridCols = 5;
	/* font size */
	public static final int fieldFontSize = 20;
	public static final int buttonFontSize = 15;
	public static final int radiobuttonFontSize = 15;
	/* configurations, PanelFillConfig[], for 4 panels */
	public static final PanelFillConfig[] btnsConfig = new PanelFillConfig[] {
		new PanelFillConfig(
				new CalcOp.Type[] {CalcOp.Type.DIGIT_BINARY, CalcOp.Type.DIGIT_OCTAL, CalcOp.Type.DIGIT_DECIMAL},
				0, 0, 3, 3, PanelFillConfig.Direction.DOWN, PanelFillConfig.Direction.RIGHT),
		new PanelFillConfig(
				new CalcOp.Type[] {CalcOp.Type.DIGIT_UNARY, CalcOp.Type.DIGIT_UNARYSEQ}, 3, 0, 3, PanelFillConfig.Direction.RIGHT),
		new PanelFillConfig(CalcOp.Type.DIGIT_HEXADECIMAL, 0, 4, 6, PanelFillConfig.Direction.DOWN),
		new PanelFillConfig(CalcOp.Type.OPERATOR_UNARY, 4, 0, 3, PanelFillConfig.Direction.RIGHT),
		new PanelFillConfig(new CalcOp.Type[] {CalcOp.Type.OPERATOR_BINARY_A, CalcOp.Type.OPERATOR_UNARY_B}, 0, 4, 6, PanelFillConfig.Direction.DOWN),
		new PanelFillConfig(CalcOp.Type.META_RETURN, 5, 0).setSpan(3)
	};
	public static final PanelFillConfig[] radiobtnsConfig1 = new PanelFillConfig[] {
		new PanelFillConfig(new CalcOp.Type[] {CalcOp.Type.META_OPTION_RADIXSYS}, 0, 0, 4, PanelFillConfig.Direction.DOWN)
	};
	public static final PanelFillConfig[] radiobtnsConfig2 = new PanelFillConfig[] {
		new PanelFillConfig(new CalcOp.Type[] {CalcOp.Type.META_OPTION_ANGLESYS}, 0, 0, 2, PanelFillConfig.Direction.DOWN)
	};
	public static final PanelFillConfig[] sidebtnsConfig = new PanelFillConfig[] {
		new PanelFillConfig(CalcOp.Type.META_CLEAR, 0, 0)
	};
	/* CalcOp instances, buttons and radio buttons */
	public static final CalcOp[] instances = new CalcOp[] {
		new CalcOpButton(CalcOp.Type.DIGIT_UNARY, "0", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("0"); return 0; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_UNARYSEQ, "00", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("00"); return 0; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_UNARYSEQ, "000", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("000"); return 0; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_BINARY, "1", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("1"); return 1; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_OCTAL, "2", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("2"); return 2; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_OCTAL, "3", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("3"); return 3; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_OCTAL, "4", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("4"); return 4; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_OCTAL, "5", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("5"); return 5; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_OCTAL, "6", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("6"); return 6; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_OCTAL, "7", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("7"); return 7; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_DECIMAL, "8", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("8"); return 8; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_DECIMAL, "9", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("9"); return 9; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_HEXADECIMAL, "A", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("A"); return 10; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_HEXADECIMAL, "B", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("B"); return 11; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_HEXADECIMAL, "C", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("C"); return 12; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_HEXADECIMAL, "D", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("D"); return 13; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_HEXADECIMAL, "E", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("E"); return 14; }
		}),
		new CalcOpButton(CalcOp.Type.DIGIT_HEXADECIMAL, "F", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.put("F"); return 15; }
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_UNARY, "sin", new CalcOp.CallbackUnary() {
			public double operate(double arg0) {
				return Math.sin(CalcCore.syscAngle(arg0));
			}
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_UNARY, "cos", new CalcOp.CallbackUnary() {
			public double operate(double arg0) {
				return Math.cos(CalcCore.syscAngle(arg0));
			}
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_UNARY, "☆", new CalcOp.CallbackUnary() {
			public double operate(double arg0) {
				return Math.sqrt(arg0);
			}
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_BINARY_A, "+", new CalcOp.CallbackBinary() {
			public double operate(double arg0, double arg1) {
				return arg0 + arg1;
			}
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_BINARY_A, "-", new CalcOp.CallbackBinary() {
			public double operate(double arg0, double arg1) {
				return arg0 - arg1;
			}
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_BINARY_A, "*", new CalcOp.CallbackBinary() {
			public double operate(double arg0, double arg1) {
				return arg0 * arg1;
			}
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_BINARY_A, "/", new CalcOp.CallbackBinary() {
			public double operate(double arg0, double arg1) {
				return arg0 / arg1;
			}
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_UNARY_B, "<<", new CalcOp.CallbackUnary() {
			public double operate(double arg0) {
				return (int)arg0 << 1;
			}
		}),
		new CalcOpButton(CalcOp.Type.OPERATOR_UNARY_B, ">>", new CalcOp.CallbackUnary() {
			public double operate(double arg0) {
				return (int)arg0 >> 1;
			}
		}),
		new CalcOpButton(CalcOp.Type.META_RETURN, "=", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.flush(); CalcCore.refresh(); return 0; }
		}),
		new CalcOpButton(CalcOp.Type.META_CLEAR, "CE", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.reset(); return 0; }
		}),
		new CalcOpRadioButton(CalcOp.Type.META_OPTION_RADIXSYS, "16柳过", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.setRadixSys(CalcCore.RadixSys.HEXADECIMAL); return 0; }
		}),
		new CalcOpRadioButton(CalcOp.Type.META_OPTION_RADIXSYS, "10柳过", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.setRadixSys(CalcCore.RadixSys.DECIMAL); return 0; }
		}),
		new CalcOpRadioButton(CalcOp.Type.META_OPTION_RADIXSYS, "8柳过", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.setRadixSys(CalcCore.RadixSys.OCTAL); return 0; }
		}),
		new CalcOpRadioButton(CalcOp.Type.META_OPTION_RADIXSYS, "2柳过", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.setRadixSys(CalcCore.RadixSys.BINARY); return 0; }
		}),
		new CalcOpRadioButton(CalcOp.Type.META_OPTION_ANGLESYS, "degree", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.setAngleSys(CalcCore.AngleSys.DEGREE); return 0; }
		}),
		new CalcOpRadioButton(CalcOp.Type.META_OPTION_ANGLESYS, "radian", new CalcOp.CallbackNonary() {
			public double operate() { CalcCore.setAngleSys(CalcCore.AngleSys.RADIAN); return 0; }
		})
	};
}

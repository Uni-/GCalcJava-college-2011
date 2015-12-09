
public interface CalcOp {
	/* every CalcOp subclass interface must be able to return its type and callback */
	public Type type();
	public Callback callback();
	public static enum Type {
		/* types of operators or operand digits */
		UNDEFINED,
		DIGIT_UNARY,
		DIGIT_UNARYSEQ,
		DIGIT_BINARY,
		DIGIT_OCTAL,
		DIGIT_DECIMAL,
		DIGIT_HEXADECIMAL,
		OPERATOR_UNARY,
		OPERATOR_BINARY_A,
		OPERATOR_UNARY_B,
		META_CLEAR,
		META_RETURN,
		META_OPTION_RADIXSYS,
		META_OPTION_ANGLESYS;
		public int requiredArgNum() {
			/* used knowing the required Callback-subinterfaces; CallbackNonary, CallbackUnary, CallbackBinary */
			switch(this) {
			case META_CLEAR: case META_RETURN: case META_OPTION_RADIXSYS: case META_OPTION_ANGLESYS:
				return -1;
			case DIGIT_UNARY: case DIGIT_UNARYSEQ: case DIGIT_BINARY: case DIGIT_OCTAL: case DIGIT_DECIMAL: case DIGIT_HEXADECIMAL: default:
				return 0;
			case OPERATOR_UNARY: case OPERATOR_UNARY_B:
				return 1;
			case OPERATOR_BINARY_A:
				return 2;
			}
		}
	}
	public abstract static interface Callback {} /* only used for handling subinterface instances */
	public static interface CallbackNonary extends Callback { /* nonary operators a.k.a. operand digits and return */
		public double operate();
	}
	public static interface CallbackUnary extends Callback { /* unary operators (and functions) */
		public double operate(double arg0);
	}
	public static interface CallbackBinary extends Callback { /* binary operators */
		public double operate(double arg0, double arg1);
	}
}

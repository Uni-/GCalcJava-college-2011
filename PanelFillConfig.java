
public class PanelFillConfig {
	private static final int dimension = 2; /* this class is a construct for dimension 2 */
	private CalcOp.Type[] types;
	private int beginTop;
	private int beginLeft;
	private int rows;
	private int cols;
	private Direction first;
	private Direction second;
	public int span = 1;
	public String title = null;
	public CalcOp.Type[] types() { return types; }
	public int beginTop() { return beginTop; }
	public int beginLeft() { return beginLeft; }
	public int rows() { return rows; }
	public int cols() { return cols; }
	public Direction first() { return first; }
	public Direction second() { return second; }
	public static enum Direction {
		/* processing direction */
		LEFT, RIGHT, UP, DOWN;
	}
	public PanelFillConfig(CalcOp.Type type, int top, int left) {
		this(new CalcOp.Type[] {type}, top, left, 1, 1, null, null);
	}
	public PanelFillConfig(CalcOp.Type type, int beginTop, int beginLeft, int length, Direction first) {
		this(new CalcOp.Type[] {type}, beginTop, beginLeft, length, first);
	}
	public PanelFillConfig(CalcOp.Type[] types, int beginTop, int beginLeft, int length, Direction first) {
		this(types, beginTop, beginLeft, (first == Direction.UP || first == Direction.DOWN ? length : 1), (first == Direction.LEFT || first == Direction.RIGHT ? length : 1), first, null);
	}
	public PanelFillConfig(CalcOp.Type[] types, int beginTop, int beginLeft, int rows, int cols, Direction first, Direction second) {
		/* the main constructor */
		this.types = types;
		this.beginTop = beginTop;
		this.beginLeft = beginLeft;
		this.rows = rows;
		this.cols = cols;
		this.first = first;
		this.second = second;
	}
	public PanelFillConfig setSpan(int span) {
		this.span = span;
		return this;
	}
	public PanelFillConfig setTitle(String title) {
		this.title = title;
		return this;
	}
	public boolean contains(int rowIndex, int colIndex) {
		return (beginTop <= rowIndex) && (beginTop + rows - 1 >= rowIndex) &&
				(beginLeft <= colIndex) && (beginLeft + cols - 1 >= colIndex);
	}
	public int count(int rowIndex, int colIndex) {
		/* get the cell position in instance's defined space */
		int count = 0;
		for (int x = 0; x < dimension; x++) {
			Direction direction = new Direction[] {first, second} [x];
			if (direction == null) continue;
			switch(direction) {
			case LEFT: case RIGHT:
				count *= cols;
				break;
			case UP: case DOWN:
				count *= rows;
				break;
			}
			switch(direction) {
			case LEFT:
				count += beginLeft + cols - colIndex - 1;
				break;
			case RIGHT:
				count += colIndex - beginLeft;
				break;
			case UP:
				count += beginTop + rows - rowIndex - 1;
				break;
			case DOWN:
				count += rowIndex - beginTop;
				break;
			}
		}
		return count;
	}
	public boolean hasType(CalcOp.Type type) {
		for (CalcOp.Type typeElement : types)
			if (typeElement == type)
				return true;
		return false;
	}
	public static void apply(PanelFillConfig[] config, javax.swing.JPanel panel, CalcOp[] instances, String className, int gridRows, int gridCols, boolean forceResize) {
		apply(config, panel, instances, className, gridRows, gridCols, 0, 0, forceResize);
	}
	public static void apply(PanelFillConfig[] config, javax.swing.JPanel panel, CalcOp[] instances, String className, int gridRows, int gridCols, int hgap, int vgap, boolean forceResize) {
		/* look panel as grid, gridRows * gridCols, given gaps
		 * and put each CalcOp instance on a cellular position of PanelFillConfig[] config */
		for (int rowIndex = 0; rowIndex < gridRows; rowIndex++)
			for (int colIndex = 0; colIndex < gridCols; colIndex++)
				for (PanelFillConfig cf : config)
					if (cf.contains(rowIndex, colIndex)) {
						int matchCnt = 0;
						for (CalcOp i : instances) {
							if (cf.hasType(i.type())) {
								if (matchCnt == cf.count(rowIndex, colIndex)) {
									if (i.getClass().toString().matches("class " + className)) {
										if (forceResize) /* if the instance should be resized, */
											((java.awt.Component)i).setPreferredSize(
												new java.awt.Dimension(
													(panel.getPreferredSize().width - hgap * (gridCols + 1)) / gridCols * cf.span + hgap * (cf.span - 1),
													(panel.getPreferredSize().height - vgap * (gridRows + 1)) / gridRows
												) /* resize it considering gridRows, gridCols, hgap, vgap */
											);
										panel.add((java.awt.Component)i);
									}
									break;
								}
								else {
									matchCnt++;
								}
							}
						}
					}
	}
}

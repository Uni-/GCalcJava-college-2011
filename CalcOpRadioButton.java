
@SuppressWarnings("serial")
public class CalcOpRadioButton extends javax.swing.JRadioButton implements CalcOp {
	/* radio button */
	private Type type;
	private Callback callback;
	public Type type() { return type; }
	public Callback callback() { return callback; }
	private CalcOpRadioButton() {
		this.addMouseListener(new mouseAdapter(this));
	}
	public CalcOpRadioButton(Type type, String name) {
		this();
		this.type = type;
		CalcCore.buttonGroup(type).add(this);
		this.setText(name);
		this.setFont(new java.awt.Font(this.getFont().getFontName(), 0, CalcPreset.radiobuttonFontSize));
	}
	public CalcOpRadioButton(Type type, String name, Callback callback) {
		this(type, name);
		this.callback = callback;
	}
	public static class mouseAdapter extends java.awt.event.MouseAdapter {
		public CalcOp host;
		public mouseAdapter(CalcOp host) {
			this.host = host;
		}
		public void mouseClicked(java.awt.event.MouseEvent arg0) {
			/* when clicked, push the event host instance to the Core */
			if (((CalcOpRadioButton)host).isEnabled())
				CalcCore.push(host);
		}
	}
}

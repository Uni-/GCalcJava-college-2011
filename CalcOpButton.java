
@SuppressWarnings("serial")
public class CalcOpButton extends javax.swing.JButton implements CalcOp {
	/* button */
	private Type type;
	private Callback callback;
	public Type type() { return type; }
	public Callback callback() { return callback; }
	private CalcOpButton() {
		this.addMouseListener(new mouseAdapter(this));
	}
	public CalcOpButton(Type type, String name) {
		this();
		this.type = type;
		this.setText(name);
		this.setFont(new java.awt.Font("Consolas", 0, CalcPreset.buttonFontSize));
	}
	public CalcOpButton(Type type, String name, Callback callback) {
		this(type, name);
		this.callback = callback;
	}
	public static class mouseAdapter extends java.awt.event.MouseAdapter {
		public CalcOp host;
		public mouseAdapter(CalcOp host) {
			this.host = host;
		}
		public void mouseClicked(java.awt.event.MouseEvent arg0) {
			/* when clicked, push the event host instance to the core */
			if (((CalcOpButton)host).isEnabled())
				CalcCore.push(host);
		}
	}
}

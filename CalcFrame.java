import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

@SuppressWarnings("serial")
public class CalcFrame extends JFrame {
	protected CalcDisplay area;
	public CalcDisplay getDisplay() {
		return area;
	}
	private CalcDisplay setDisplay(CalcDisplay area) {
		return this.area = area;
	}
	public CalcFrame() {
		init();
		load();
	}
	private synchronized void init() {
		setSize(CalcPreset.frmWidth, CalcPreset.frmHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	private synchronized void load() {
		JPanel
			mCalcPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, CalcPreset.layUGap, CalcPreset.layUGap)),
			mCalcPanelText = new JPanel(new FlowLayout(FlowLayout.RIGHT, CalcPreset.panTextPadding, CalcPreset.panTextPadding)),
			mCalcPanelOp = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)),
			mCalcPanelA = new JPanel(new FlowLayout(FlowLayout.LEFT, CalcPreset.laySGap, CalcPreset.laySGap)),
			mCalcPanelB = new JPanel(new FlowLayout(FlowLayout.RIGHT, CalcPreset.laySGap, CalcPreset.laySGap)),
			mCalcPanelSr = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)),
			mCalcPanelSa = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)),
			mCalcPanelT = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		CalcDisplay calcField = new CalcDisplay();
		this.setDisplay(calcField);
		mCalcPanelText.add(calcField);

		mCalcPanelA.add(mCalcPanelSr);
		mCalcPanelA.add(mCalcPanelSa);
		mCalcPanelA.add(mCalcPanelT);
		mCalcPanelOp.add(mCalcPanelA);
		mCalcPanelOp.add(mCalcPanelB);
		mCalcPanelContainer.add(mCalcPanelText);
		mCalcPanelContainer.add(mCalcPanelOp);
		this.add(mCalcPanelContainer);

		mCalcPanelText.setBackground(Color.WHITE);
		mCalcPanelText.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		mCalcPanelSr.setBorder(BorderFactory.createTitledBorder(""));
		mCalcPanelSa.setBorder(BorderFactory.createTitledBorder(""));
		
		mCalcPanelText.setPreferredSize(new Dimension(this.getContentPane().getWidth() - (CalcPreset.layUGap + CalcPreset.laySGap) * 2, CalcPreset.panTextHeight));
		mCalcPanelOp.setPreferredSize(new Dimension(this.getContentPane().getWidth() - CalcPreset.layUGap * 2, this.getContentPane().getHeight() - (CalcPreset.panTextHeight + CalcPreset.layUGap * 3)));
		mCalcPanelA.setPreferredSize(new Dimension(mCalcPanelOp.getPreferredSize().width - CalcPreset.panBWidth - 2, mCalcPanelOp.getPreferredSize().height));
		mCalcPanelB.setPreferredSize(new Dimension(CalcPreset.panBWidth, mCalcPanelOp.getPreferredSize().height));
		mCalcPanelSr.setPreferredSize(new Dimension(mCalcPanelA.getPreferredSize().width - CalcPreset.layUGap, (mCalcPanelA.getPreferredSize().height - CalcPreset.laySGap * (CalcPreset.panBGridRows + 1)) / CalcPreset.panBGridRows * 3 + CalcPreset.laySGap * 2));
		mCalcPanelSa.setPreferredSize(new Dimension(mCalcPanelA.getPreferredSize().width - CalcPreset.layUGap, (mCalcPanelA.getPreferredSize().height - CalcPreset.laySGap * (CalcPreset.panBGridRows + 1)) / CalcPreset.panBGridRows * 2 + CalcPreset.laySGap));
		mCalcPanelT.setPreferredSize(new Dimension(mCalcPanelA.getPreferredSize().width - CalcPreset.layUGap, (mCalcPanelA.getPreferredSize().height - CalcPreset.laySGap * (CalcPreset.panBGridRows + 1)) / CalcPreset.panBGridRows));

		PanelFillConfig.apply(CalcPreset.btnsConfig, mCalcPanelB, CalcPreset.instances, "CalcOpButton", CalcPreset.panBGridRows, CalcPreset.panBGridCols, CalcPreset.laySGap, CalcPreset.laySGap, true);
		PanelFillConfig.apply(CalcPreset.radiobtnsConfig1, mCalcPanelSr, CalcPreset.instances, "CalcOpRadioButton", CalcPreset.panSrGridRows, CalcPreset.panSrGridCols, false);
		PanelFillConfig.apply(CalcPreset.radiobtnsConfig2, mCalcPanelSa, CalcPreset.instances, "CalcOpRadioButton", CalcPreset.panSaGridRows, CalcPreset.panSaGridCols, false);
		PanelFillConfig.apply(CalcPreset.sidebtnsConfig, mCalcPanelT, CalcPreset.instances, "CalcOpButton", CalcPreset.panTGridRows, CalcPreset.panTGridCols, true);
	}
}

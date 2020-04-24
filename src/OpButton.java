import java.awt.*;

public class OpButton extends RoundButton {
    public OpButton(String label) {
        super(label);
        unchoose();
    }

    public void choose() {
        setBackground(Color.WHITE);
        setForeground(Main.ORANGE);
        Calc.operator = getText().charAt(0);
    }

    public void unchoose() {
        setBackground(Main.ORANGE);
        setForeground(Color.WHITE);
    }

    public void cancel() {
        unchoose();
        Calc.operator = ' ';
    }
}

package View;

import java.awt.Dimension;
import java.awt.Font;

import org.json.simple.JSONObject;

import Control.ImagePrint;

@SuppressWarnings("serial")
public class ScorePanel extends SendDataPanel {

	BasicJLabel title;
	BasicJLabel score;

	public ScorePanel() {

		int height = 70;

		setOpaque(false);
		setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height));

		title = new BasicJLabel("점수", Font.BOLD, 23);
		score = new BasicJLabel("0", Font.BOLD, 23);

		title.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));
		score.setPreferredSize(new Dimension(ImagePrint.WIDTH * 5, height / 2));

		add(title);
		add(score);

	}

	@Override
	void setData(Object obj) {
		JSONObject event = (JSONObject) obj;
		score.setText(event.get("Score").toString());
		repaint();
	}
}

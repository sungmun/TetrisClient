package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import Control.Point;
import Control.TetrisControlManager;
import Model.Block;
import Model.Map;
import Model.Space;

public class TetrinoBlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static TetrinoBlockPanel tetrinoblockpanel = null;
	TetrisControlManager manager = TetrisControlManager.createTetrisControlManager();
	int width, height;
	BufferedImage buffer = null;
	public static BlockView[][] squareList = new BlockView[Map.getMapHeight() - 3][Map.getMapWidth()];

	private TetrinoBlockPanel(int width, int height) {
		for (int y = 0; y < Map.getMapHeight() - 3; y++) {
			for (int x = 0; x < Map.getMapWidth(); x++) {
				squareList[y][x] = new BlockView(width, height, new Point(y, x));
			}
		}
		this.width = width;
		this.height = height;
	}

	public static TetrinoBlockPanel getTetrinoBlockPanel() {
		return tetrinoblockpanel;
	}

	public static TetrinoBlockPanel createTetrinoBlockPanel(int width, int height) {
		if (tetrinoblockpanel == null) {
			tetrinoblockpanel = new TetrinoBlockPanel(width, height);
		}
		tetrinoblockpanel.addKeyListener(new KeyBoardEvent());
		return tetrinoblockpanel;
	}

	public void blockViewCheck() {
		Point pos = manager.getNowTetrino().getFlowTetrino();
		Rectangle rec = manager.getNowTetrino().getActivespace();
		// rec는 그림을 그려야 하는 범위를 표시한다
		// pos는 현제 테트리노의 위치를 알려준다
		int repaintx = pos.getX() + (int) rec.getX() - 3;
		int repainty = pos.getY() + (int) rec.getY() - 3;
		// 이부분에서의 x와 y의 값은 repaint를 해야하는 부분이다

		for (int y = repainty; y < (int) rec.getHeight() + repainty; y++) {
			for (int x = repaintx; x < (int) rec.getWidth() + repaintx; x++) {
				Space spc = manager.getRealTimeMap()[y][x];
				if (y < 3) {
					break;
				}
				if (spc.getIsblock() != Space.ETC) {
					Color col = new Color(255, 0, 0, 0);
					if (spc.getIsblock() != Space.SPACE) {
						col = BlockColor.SetBlockColor(((Block) spc).getType());
					}
					squareList[y - 3][x].BlockChange(col);
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (BlockView[] blockViews : squareList) {
			for (BlockView bV : blockViews) {
				
				g.setColor(bV.getCol());
				g.fill3DRect((bV.pos.getX())*bV.getDpWidth(), (bV.pos.getY())*bV.getDpHeight(), bV.getDpWidth(), bV.getDpHeight(), true);
			}
		}
	}
}

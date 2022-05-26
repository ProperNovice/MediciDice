package panel;

import controllers.Credentials;
import enums.EColor;
import enums.EPlayer;
import utils.ListImageViewAbles;
import utils.ObjectPool;
import utils.Vector2;

public class PanelDiceColor {

	private ListImageViewAbles<AIcon> listX = null;
	private ListImageViewAbles<AIcon> listO = null;

	public PanelDiceColor(EColor eColor, Vector2 coordinates) {

		new ColorEmpty(eColor).getImageView().relocateTopLeft(coordinates);

		coordinates.x += Credentials.INSTANCE.dIcon;
		coordinates.x += Credentials.INSTANCE.dGapBetweenComponents.x;

		// list x

		this.listX = new ListImageViewAbles<>();
		this.listX.getListCredentials().coordinatesList = coordinates;

		// list o

		this.listO = new ListImageViewAbles<>();
		this.listO.getListCredentials().coordinatesList = coordinates;

	}

	public void addScorePlayer() {
		addScoreRelocate(IconX.class, this.listX);
	}

	public void addScoreBot() {
		addScoreRelocate(IconO.class, this.listO);
	}

	public EPlayer getPlayerWinner() {

		if (this.listX.getArrayList().size() >= this.listO.getArrayList().size())
			return EPlayer.HUMAN;
		else
			return EPlayer.BOT;

	}

	private void addScoreRelocate(Class<? extends AIcon> iconClass,
			ListImageViewAbles<AIcon> list) {

		AIcon icon = ObjectPool.INSTANCE.acquire(iconClass);

		list.getArrayList().addLast(icon);
		list.relocateImageViews();

	}

}

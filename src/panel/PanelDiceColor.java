package panel;

import controllers.Credentials;
import enums.EColor;
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
		this.listX.getArrayList().setCapacity(12);

		// list o

		this.listO = new ListImageViewAbles<>();
		this.listO.getListCredentials().coordinatesList = coordinates;
		this.listX.getArrayList().setCapacity(12);

	}

	public void addScoreOneToPlayer() {
		addScoreRelocate(IconX.class, this.listX);
	}

	public void addScoreOneToBot() {
		addScoreRelocate(IconO.class, this.listO);
	}

	public int getScoreHuman() {
		return this.listX.getArrayList().size();
	}

	public int getScoreBot() {
		return this.listO.getArrayList().size();
	}

	private void addScoreRelocate(Class<? extends AIcon> iconClass,
			ListImageViewAbles<AIcon> list) {

		if (list.getArrayList().isMaxedCapacity())
			return;

		AIcon icon = ObjectPool.INSTANCE.acquire(iconClass);

		list.getArrayList().addLast(icon);
		list.relocateImageViews();

	}

}

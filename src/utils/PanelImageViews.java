package utils;

import controllers.Credentials;
import enums.ELayerZ;
import utils.Enums.RearrangeTypeEnum;
import utils.Interfaces.IImageViewAble;

public enum PanelImageViews {

	INSTANCE;

	private Background background = null;
	private ListImageViewAbles<IImageViewAble> panel = null;
	private ELayerZ eLayerZList = null;
	private ListImageViewAbles<? extends Object> listOriginal = null;

	private PanelImageViews() {

		this.background = new Background(ELayerZ.PANEL);
		this.background.getImageView().setVisible(false);

		this.panel = new ListImageViewAbles<>();
		this.panel.getListCredentials().coordinatesList = new Vector2(
				Credentials.INSTANCE.dFrame.x / 2, Credentials.INSTANCE.dFrame.y / 2);
		this.panel.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;

	}

	public void setListToPanel(ListImageViewAbles<? extends Object> list) {

		this.background.getImageView().setVisible(true);

		this.eLayerZList = LayerZ.INSTANCE
				.getImageViewELayerZ((IImageViewAble) list.getArrayList().getFirst());

		this.listOriginal = list;
		this.listOriginal.getArrayList().saveState();

		while (!list.getArrayList().isEmpty()) {

			IImageViewAble imageViewAble = (IImageViewAble) list.getArrayList().removeFirst();
			LayerZ.INSTANCE.transferImageViewToLayer(imageViewAble, ELayerZ.PANEL);

			this.panel.getArrayList().addLast(imageViewAble);

		}

		this.panel.relocateImageViews();

	}

	public void clearPanel() {

		this.background.getImageView().setVisible(false);
		this.panel.getArrayList().clear();

		this.listOriginal.getArrayList().loadState();

		for (Object object : this.listOriginal) {

			IImageViewAble imageViewAble = (IImageViewAble) object;
			LayerZ.INSTANCE.transferImageViewToLayer(imageViewAble, this.eLayerZList);

		}

		this.listOriginal.relocateImageViews();

	}

	public ListImageViewAbles<IImageViewAble> getPanel() {
		return this.panel;
	}

}

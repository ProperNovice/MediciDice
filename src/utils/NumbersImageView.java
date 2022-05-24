package utils;

import controllers.Credentials;
import enums.ELayerZ;
import utils.Interfaces.IImageViewAble;

public enum NumbersImageView {

	INSTANCE;

	private ArrayList<NumberImageView> listNumberImageView = new ArrayList<>();
	private ArrayList<MinusImageView> listMinusImageView = new ArrayList<>();

	private NumbersImageView() {

	}

	public IImageViewAble getNumberImageView(int number) {

		NumberImageView numberImageView = null;

		for (NumberImageView numberImageViewTemp : this.listNumberImageView) {

			if (numberImageViewTemp.getNumber() != number)
				continue;

			if (numberImageViewTemp.getImageView().isVisible())
				continue;

			numberImageView = numberImageViewTemp;
			numberImageView.getImageView().setVisible(true);
			break;

		}

		if (numberImageView == null) {

			numberImageView = new NumberImageView(number);
			this.listNumberImageView.addLast(numberImageView);

		}

		return numberImageView;

	}

	public IImageViewAble getMinusImageView() {

		MinusImageView minusImageView = null;

		for (MinusImageView minusImageViewTemp : this.listMinusImageView) {

			if (minusImageViewTemp.getImageView().isVisible())
				continue;

			minusImageView = minusImageViewTemp;
			minusImageView.getImageView().setVisible(true);
			break;

		}

		if (minusImageView == null) {

			minusImageView = new MinusImageView();
			this.listMinusImageView.addLast(minusImageView);

		}

		return minusImageView;

	}

	private class NumberImageView implements IImageViewAble {

		private int number = -1;

		public NumberImageView(int number) {

			this.number = number;

			String fileName = "misc/numbers/";
			fileName += Credentials.INSTANCE.numbersImageViewColor;
			fileName += "/";
			fileName += number;
			fileName += ".png";

			new ImageView(fileName, ELayerZ.ICONS_MISC, this);

		}

		public int getNumber() {
			return this.number;
		}

	}

	private class MinusImageView implements IImageViewAble {

		public MinusImageView() {

			String fileName = "misc/numbers/";
			fileName += Credentials.INSTANCE.numbersImageViewColor;
			fileName += "/-.png";

			new ImageView(fileName, ELayerZ.ICONS_MISC, this);

		}

	}

}

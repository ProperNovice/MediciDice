package utils;

import java.util.Iterator;

import controllers.Credentials;
import controllers.Lists;
import utils.Enums.AnimationSynchEnum;
import utils.Enums.ImageViewActionEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;
import utils.Interfaces.IImageViewAble;

public class ListImageViewAbles<T> implements Iterable<T> {

	private ArrayListImageView<T> arrayList = new ArrayListImageView<T>(() -> showListSize());
	private PanelNumbers panelNumbers = new PanelNumbers();
	private CoordinatesList<T> coordinates = new CoordinatesList<>(this);
	private ListCredentials listCredentials = new ListCredentials();

	public ListImageViewAbles() {

		Lists.INSTANCE.lists.addLast(this.arrayList);
		RealTimeDuplicateProtection.INSTANCE.addList(this.arrayList);

	}

	public void layerZSort() {

		switch (this.listCredentials.layerZListEnum) {

		case TO_FRONT_FIRST_IMAGEVIEW:
			toFrontFirstImageView();
			break;

		case TO_BACK_FIRST_IMAGEVIEW:
			toBackFirstImageView();
			break;

		}

	}

	private void toFrontFirstImageView() {

		IImageViewAble imageViewAble = null;

		for (int counter = this.arrayList.size() - 1; counter >= 0; counter--) {

			imageViewAble = (IImageViewAble) this.arrayList.get(counter);
			imageViewAble.getImageView().toFront();

			toFrontDependency(imageViewAble);

		}

	}

	private void toBackFirstImageView() {

		IImageViewAble imageViewAble = null;

		for (T t : this.arrayList) {

			imageViewAble = (IImageViewAble) t;
			imageViewAble.getImageView().toFront();

			toFrontDependency(imageViewAble);

		}

	}

	private void toFrontDependency(IImageViewAble imageViewAble) {

		if (!ImageViewAbleDependency.INSTANCE.containsDependency(imageViewAble))
			return;

		for (IImageViewAble dependency : ImageViewAbleDependency.INSTANCE
				.getDependency(imageViewAble))
			dependency.getImageView().toFront();

	}

	public final void animateAsynchronous() {
		executeAction(ImageViewActionEnum.ANIMATE, AnimationSynchEnum.ASYNCHRONOUS);
	}

	public final void animateSynchronous() {
		executeAction(ImageViewActionEnum.ANIMATE, AnimationSynchEnum.SYNCHRONOUS);
	}

	public final void animateSynchronousLock() {

		animateSynchronous();
		Lock.INSTANCE.lock();

	}

	public final void relocateImageViews() {
		executeAction(ImageViewActionEnum.RELOCATE, null);
	}

	private void showListSize() {

		if (!this.listCredentials.showListSize)
			return;

		if (this.arrayList.isEmpty()) {

			this.panelNumbers.clear();
			return;

		}

		// set list credentials

		IImageViewAble imageViewAble = (IImageViewAble) this.arrayList.getRandom();
		ImageView imageView = imageViewAble.getImageView();

		Vector2 center = this.listCredentials.coordinatesList.clone();

		RelocateTypeEnum relocateTypeEnum = this.listCredentials.relocateTypeEnum;

		center = CoordinatesRelocateType.INSTANCE.translateRelocateTypeCoordinatesFindCenter(center,
				imageViewAble.getImageView().getDimenions(), relocateTypeEnum);

		this.panelNumbers.getListCredentials().rearrangeTypeEnum = RearrangeTypeEnum.PIVOT;
		this.panelNumbers.getListCredentials().coordinatesList = center;
		this.panelNumbers.getListCredentials();

		// set number

		int number = this.arrayList.size();

		double dimension = Math.min(imageView.getWidth(), imageView.getHeight());
		dimension *= Credentials.INSTANCE.listQuantityRatioDimensions;

		this.panelNumbers.setNumber(number, dimension);

	}

	private void executeAction(ImageViewActionEnum imageViewAction,
			AnimationSynchEnum animationSynch) {

		ArrayList<T> list = this.arrayList.clone();
		list.reverse();

		for (T t : list) {

			int index = this.arrayList.indexOf(t);
			Vector2 vector2 = this.coordinates.getCoordinate(index);

			IImageViewAble imageViewAble = (IImageViewAble) t;

			switch (imageViewAction) {

			case ANIMATE:

				imageViewAble.getImageView().getAnimationExecutionObject()
						.setAnimationCredentials(vector2);

				if (imageViewAble.getImageView().getAnimationExecutionObject()
						.animationIsFinished())
					continue;
				else
					Animation.INSTANCE.animateTopLeft(imageViewAble, vector2, animationSynch);
				break;

			case RELOCATE:

				imageViewAble.getImageView().relocateTopLeft(vector2);
				showListSize();

				if (!ImageViewAbleDependency.INSTANCE.containsDependency(imageViewAble))
					break;

				for (IImageViewAble dependency : ImageViewAbleDependency.INSTANCE
						.getDependency(imageViewAble))
					dependency.getImageView().relocateTopLeft(vector2);

				break;

			}

		}

		layerZSort();

	}

	public final ArrayList<T> getArrayList() {
		return this.arrayList;
	}

	public final ListCredentials getListCredentials() {
		return this.listCredentials;
	}

	@Override
	public final Iterator<T> iterator() {
		return this.arrayList.iterator();
	}

}

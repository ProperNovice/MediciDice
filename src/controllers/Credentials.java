package controllers;

import utils.ArrayList;
import utils.Background;
import utils.Enums.RearrangeTypeEnum;
import utils.SelectImageView;
import utils.Vector2;

public enum Credentials {

	INSTANCE;

	public final String primaryStageTitle = "Medici Dice", numbersImageViewColor = "black";
	public final boolean colliderVisibility = true;
	public final double gapBetweenBorders = 25, textHeight = 50,
			selectEventHandlerAbleDimension = 100, listQuantityRatioDimensions = 0.5,
			animationStep = 4;
	public ArrayList<Class<?>> lineCastExcludeList = new ArrayList<Class<?>>();
	public RearrangeTypeEnum rearrangeTypeEnumText = RearrangeTypeEnum.LINEAR;
	public Vector2 dFrame, dGapBetweenComponents, dGapBetweenComponentsLineCast;
	public Vector2 cTextPanel, cDice;
	public double dDice;

	private Credentials() {

		this.lineCastExcludeList.addLast(SelectImageView.class);
		this.lineCastExcludeList.addLast(Background.class);

		double x = 0, y = 0;

		this.dFrame = new Vector2(1920, 1368);
		this.dGapBetweenComponents = new Vector2(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		this.cTextPanel = new Vector2(x, y);

		// d dice

		this.dDice = 150;

		// c dice

		x = this.gapBetweenBorders;
		y = this.dFrame.y;
		y -= this.gapBetweenBorders;
		y -= this.dDice;
		this.cDice = new Vector2(x, y);

	}

}

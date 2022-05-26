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
	public Vector2 cTextPanel, cDice, cPanelDiceColor, cPanelDiceCapacity, cPanelScore;
	public double dIcon;

	private Credentials() {

		this.lineCastExcludeList.addLast(SelectImageView.class);
		this.lineCastExcludeList.addLast(Background.class);

		double x = 0, y = 0;

		this.dFrame = new Vector2(1920, 1368);
		this.dGapBetweenComponents = new Vector2(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		this.cTextPanel = new Vector2(x, y);

		// d icon

		this.dIcon = 100;

		// c panel dice color

		x = this.gapBetweenBorders;
		this.cPanelDiceColor = new Vector2(x, x);

		// c panel dice capacity

		x = this.gapBetweenBorders;
		y = this.cPanelDiceColor.y;
		y += 5 * (this.dIcon + this.dGapBetweenComponents.x);
		this.cPanelDiceCapacity = new Vector2(x, y);

		// c panel score

		x = this.gapBetweenBorders;
		y = this.cPanelDiceCapacity.y;
		y += this.dIcon + this.dGapBetweenComponents.x;
		this.cPanelScore = new Vector2(x, y);

		// c dice

		x = this.gapBetweenBorders;
		y = this.cPanelScore.y;
		y += this.dIcon + 15 * this.dGapBetweenComponents.x;
		this.cDice = new Vector2(x, y);

		// frame

		x = 13 * this.dIcon;
		x += 12 * this.dGapBetweenComponents.x;
		y = this.cDice.y;
		y += this.dIcon + this.gapBetweenBorders;
		this.dFrame = new Vector2(x, y);

	}

}

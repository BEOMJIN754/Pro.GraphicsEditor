package global;

import shapetools.GRectangle;
import shapetools.GShape;

public class Constants {
	public enum EShapeButtons {
		eRactangle("rectangle", new GRectangle()), // 스펠링 안틀릴 수 있음 
		eOval("oval", new GRectangle()),
		eLine("line", new GRectangle()), 
		ePolygon("polygon", new GRectangle());
	
		private String text;
		private String string;
		private GRectangle shapeTool;
		EShapeButtons(String text, GRectangle shapeTool){
			this.text = text;
			this.shapeTool = shapeTool;
		}
		
	

		public String getText() {
			return text;
		}
		public GShape getShapeTool () {
			return this.shapeTool;
		}
	}
}

package global;

import shapetools.GRectangleTool;
import shapetools.GShapeTool;

public class Constants {
	public enum EShapeButtons {
		eRactangle("rectangle", new GRectangleTool()), // 스펠링 안틀릴 수 있음 
		eOval("oval", new GRectangleTool()),
		eLine("line", new GRectangleTool()), 
		ePolygon("polygon", new GRectangleTool());
	
		private String text;
		private String string;
		private GRectangleTool shapeTool;
		EShapeButtons(String text, GRectangleTool shapeTool){
			this.text = text;
			this.shapeTool = shapeTool;
		}
		
	

		public String getText() {
			return text;
		}
		public GShapeTool getShapeTool () {
			return this.shapeTool;
		}
	}
}

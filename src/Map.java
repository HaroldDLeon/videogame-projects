import java.awt.Graphics;

public class Map {
//	ImageLayer[] layers = new ImageLayer[10];
	ImageLayer[] layers = new ImageLayer[4];
	
	public Map(){ 
		
		ImageLayer layer0 	= new ImageLayer("../assets/map/layer_0.png", 	100000);
		ImageLayer layer1 	= new ImageLayer("../assets/map/layer_1.png", 	12);
		ImageLayer layer2 	= new ImageLayer("../assets/map/layer_2.png", 	3	);	
		ImageLayer layer3 	= new ImageLayer("../assets/map/layer_4.png", 	1000);
		ImageLayer[] temp_array = {layer0, layer1, layer2, layer3};
		for (int i = 0; i < layers.length; i++) {
			this.layers[i] = temp_array[i];	
		}	
		
	}
	public void draw(Graphics g){
		for (int i = 0; i < layers.length; i++) {
			layers[i].draw(g);
		}
	}
	public void moveLeftBy(int delta_x){
		for (int i = 0; i < layers.length; i++) {	
			layers[i].moveLeftBy(delta_x);
		}
	}
	public void moveRightBy(int delta_x){
		for (int i = 0; i < layers.length; i++) {	
			layers[i].moveRightBy(delta_x);
		}
	}
}

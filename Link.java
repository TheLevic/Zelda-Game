import java.awt.image.BufferedImage;
import java.awt.Graphics;
/*
 * Levi Crider
 * 2/9/22
 * Legend of Zelda
 * 
 * 
 */
public class Link extends Sprite{

    //Variables
    int prevX;
    int prevY;
    double speed = 8;
    int AnimationNum = 0;
    static BufferedImage[] linkImages;
    public int direction; //1 is up, 2 is right, 3 is down, 4 is left

    Link(){
        this.x = 100;
        this.y = 100;
        w = 55;
        h = 70;
        prevX = x;
        prevY = y;
        loadImage();
    }

    @Override
    public boolean update(){
        for (int i = 0; i < Model.sprites.size(); i++){
            if (!Model.sprites.get(i).isLink()){
                boolean collision = Model.isThereACollision(this, Model.sprites.get(i));
                if (collision){
                    this.getOutOfBrick(Model.sprites.get(i));
                }
            }
		}
        return true;
    }

    void getOutOfBrick(Sprite b){
        //If he's going right into a brick
        if (x + w >= b.x && prevX + w <= b.x){
            x = prevX;
        }
        //If he's going left into a brick
        if (x <= b.x + b.w && prevX >= b.x + b.w){
            x = prevX;
        }

        //If his head is in a brick
        if(y <= b.y + b.h && prevY >= b.y + b.h){
            y = prevY;
        }

        //If his feet are in a brick
        if (y + h >= b.y && prevY + h <= b.y){
            y = prevY;
        }
    }

    void savePrev(){
        prevX = x;
        prevY = y;
    }

    //Direction stuff for link to throw the boomerang properly
    public int getDirection(){
        return direction;
    }
    
    public void moveUp(){
        this.direction = 1;
		this.y -= this.speed;
        if (this.AnimationNum >= 4){
            this.AnimationNum = 0;	
        }
        this.AnimationNum++;
    }
    public void moveDown(){
        //Animating link and setting directions for boomerang
        this.direction = 3;
        this.y += this.speed;
        //Animating the character
        if (this.AnimationNum >= 9 || this.AnimationNum < 5){
            this.AnimationNum = 5;	
        }
        this.AnimationNum++;
    }

    public void moveRight(){
        //Animating link and setting directions for boomerang
			this.direction = 2;
			this.x += this.speed;
			//Animating the character
			if (this.AnimationNum >= 19 || this.AnimationNum < 15){
				this.AnimationNum = 15;	
			}
			this.AnimationNum++;
    }

    public void moveLeft(){
        //Animating link and setting directions for boomerang
			this.direction = 4;
			this.x -= this.speed;
			//Animating the character
			if (this.AnimationNum >= 14 || this.AnimationNum < 10){
				this.AnimationNum = 10;	
			}
			this.AnimationNum++;
    }

    @Override
    void loadImage(){
        if (linkImages == null){
            linkImages = new BufferedImage[25];
            for (int i = 1; i < 25; i++){
                String tmp = Integer.toString(i) + ".png";
                linkImages[i - 1] = View.loadImage("linkPictures/" + tmp);
            }
        }
    }

    
    @Override
    void draw(Graphics g){
        g.drawImage(linkImages[AnimationNum], x - View.scrollPositonX, y - View.scrollPositonY, null);
    }

    @Override 
    public String toString()
    {
        return "Link (x,y) = (" + x + ", " + y + ")";
    }
    @Override 
    Json Marshal(){
        Json ob = Json.newObject();
        return ob;
    }
    @Override
    boolean isLink(){
        return true;
    }

}

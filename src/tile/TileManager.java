package tile;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;


public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/res/Map/map02.txt");
    }
    public void getTileImage(){
        try {
            tile[0] =  new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

            tile[1] =  new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] =  new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
            tile[2].collision = true;

            tile[3] =  new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png"));

            tile[4] =  new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png"));

            tile[5] =  new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));
            tile[5].collision = true;
            
        } catch (Exception e) {
        }
    }
    public void loadMap(String filepath){
       try {
        InputStream is = getClass().getResourceAsStream(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int col  = 0 ;
        int row = 0; 
        while (row < gp.maxWorldCol && col < gp.maxWorldCol ){
            String line = br.readLine();
            while ( col < gp.maxWorldCol ){
                String numbers[] = line.split(" ");
                int num = Integer.parseInt(numbers[col]);
                mapTileNum[col][row] = num;
                col++; 
            } if (col == gp.maxWorldCol){
            
                col = 0;
                row++;
            }   
        }
        br.close();
       } catch (Exception e) {
        // TODO: handle exception
       } 
    }
    public void draw(Graphics2D g2){
        int worldcol = 0;
        int worldrow = 0;
       

        while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldCol){
            int tileNum = mapTileNum[worldcol][worldrow]; 
            int worldX = worldcol * gp.tileSize;
            int worldy = worldrow * gp.tileSize;
            int screenX = worldX -gp.player.worldX + gp.player.screenX;
            int screenY = worldy -gp.player.worldY + gp.player.screenY;
            
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
                worldy + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldy - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize,gp.tileSize,null);
                }
            worldcol ++;
            if (worldcol == gp.maxWorldCol){
                worldcol = 0; 
           
                worldrow++;
                
            }
        }
    }
}

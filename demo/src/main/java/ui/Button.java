        package ui;

        import java.awt.Color;
        import java.awt.Graphics;
        import java.awt.Rectangle;

        public class Button {
            private int x,y,w,h;
            private String text;
            private Rectangle bounds;
            private boolean mouseOver;

            public Button(String text,int x, int y, int width, int height){
                this.text = text;
                this.x = x;
                this.y = y;
                this.w = width;
                this.h = height;

                initBounds();
            }

            private void initBounds(){
                this.bounds = new Rectangle(x,y,w,h);
            }

            public void draw(Graphics G){

                //body
                drawBody(G);
                
                //border
                G.setColor(Color.black);
                G.drawRect(x,y,w,h);

                drawText(G);
            }

            public void drawBody(Graphics G){
                if(mouseOver){
                    G.setColor(Color.LIGHT_GRAY);
                }
                else{
                    G.setColor(Color.WHITE);
                }
                G.fillRect(x,y,w,h);
            }

            private void drawText(Graphics G){
                int width = G.getFontMetrics().stringWidth(text);
                int height = G.getFontMetrics().getAscent();
                G.drawString(text,x+w/2-width/2,y+h/2+height/2);
            }

            public void setMouseOver(boolean mouseOver){
                this.mouseOver = mouseOver;
            }

            public Rectangle getBounds(){
                return bounds;
            }
        }

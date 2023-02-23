//Parker Gent
//10/15/22
/*TO create a side scoller that where a model of mario
 * can jump of pipes
 */

import java.util.ArrayList;

public class Model{

	ArrayList<Sprite> sprites;
	Mario mario;


	Model()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario(40, 300);
		sprites.add(mario);
	}

	public void addPipe(int mx, int my)
	{
		boolean foundPipe = false;
		for(int i = 0; i < sprites.size(); i++)
		 {
			if(sprites.get(i).isPipe())
			{
				if(((Pipe)sprites.get(i)).detectPipe(mx, my))
				{
					foundPipe = true;
					sprites.remove(i);
				}
			}
		 }

		if(!foundPipe)
		sprites.add(new Pipe(mx, my));
	}

	public void addGoomba(int mx, int my)
	{
		boolean foundGoomba = false;
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isGoomba())
			{
				if(((Goomba)sprites.get(i)).detectGoomba(mx, my))
				{
					foundGoomba = true;
					sprites.remove(i);
				}
			}
		}
		if(!foundGoomba)
			sprites.add(new Goomba(mx, my));
	}

	public void update()
	{
		for(int i = 0; i < sprites.size(); i++)
		{
			sprites.get(i).update();

			if(sprites.get(i).isGoomba())
			{
				for(int j = 0; j < sprites.size(); j++)
				{
					if(sprites.get(j).isPipe())
					{
						if(collision(sprites.get(i), sprites.get(j)))
						{
							fixCollision(sprites.get(i), sprites.get(j));
						}
					}

				}
				
			}

			if(sprites.get(i).isFireball())
			{
				for(int k = 0; k < sprites.size(); k++)
				{
					if(sprites.get(k).isGoomba())
					{
						if(collision(sprites.get(i), sprites.get(k)))
						{
							((Goomba)sprites.get(k)).dead = true;
							((Goomba)sprites.get(k)).loadFire();
						}

						if(((Goomba)sprites.get(k)).numFramesDead > 20)
							{
								((Goomba)sprites.get(k)).numFramesDead = 0;
								sprites.remove(k);
							}

					}

				}
			}

			for(int j = 0; j < sprites.size(); j++)
			{
				if(sprites.get(j).isPipe())
				{
					if(collision(mario, sprites.get(j)))
					{
						fixCollision(mario, sprites.get(j));
					}
				}
			}
		}
	}

	boolean collision(Sprite a, Sprite b)
	{
		int sprite_a_left = a.x;
		int sprite_a_right = a.x + a.w;
		int sprite_a_top = a.y;
		int sprite_a_bottom = a.y + a.h;

		int sprite_b_left = b.x;
		int sprite_b_right = b.x + b.w;
		int sprite_b_top = b.y;
		int sprite_b_bottom = b.y + b.h;

		if(sprite_a_right <= sprite_b_left){
            return false;
        }

        if(sprite_a_left >= sprite_b_right){
            return false;
        }

        if(sprite_a_bottom <= sprite_b_top){
            return false;
        }

        if(sprite_a_top >= sprite_b_bottom){
            return false;
        }
            
        return true; 
	}

	void fixCollision(Sprite a, Sprite b)
	{
		int sprite_a_left = a.x;
		int sprite_a_right = a.x + a.w;
		int sprite_a_top = a.y;
		int sprite_a_bottom = a.y + a.h;

		int sprite_b_left = b.x;
		int sprite_b_right = b.x + b.w;
		int sprite_b_top = b.y;
		int sprite_b_bottom = b.y + b.h;

		int sprite_a_prevLeft = a.prevx;
		int sprite_a_prevRight = a.prevx + a.w;
		int sprite_a_prevTop = a.prevy;
		int sprite_a_prevBottom = a.prevy + a.h;


			//USE IF STATEMENTS TO TEST AND PRINT INFORMATION
		if(sprite_a_prevRight <= sprite_b_left && sprite_a_right >= sprite_b_left)
		{
			a.x = sprite_a_prevLeft;

			if(a.isGoomba())
			{
				((Goomba)a).direction *= -1;
			}
		}

		if(sprite_a_prevLeft >= sprite_b_right  && sprite_a_left <= sprite_b_right)
		{
			a.x = a.prevx;

			if(a.isGoomba())
			{
				((Goomba)a).direction *= -1;
			}
		}

		//TOP COLLISION
		if(sprite_a_bottom >= sprite_b_top && sprite_a_prevBottom <= sprite_b_top)
		{
			a.y = sprite_b_top - a.h;
		
			if(a.isMario())
			{
				mario.vertVelocity = 0;
				mario.numFramesInAir = 0;
				return;
			}
		}

		//BOTTOM COLLISION
		if(sprite_a_top >= sprite_b_bottom && sprite_a_prevBottom <= sprite_b_bottom)
		{
			a.y = sprite_b_bottom - a.h;
		}

	}
	
	Json marshal()
	{
		Json ob = Json.newObject();
		Json tmpListPipe = Json.newList();
		ob.add("pipes", tmpListPipe);
		Json tmpListGoomba = Json.newList();
		ob.add("goombas", tmpListGoomba);
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isPipe())
				tmpListPipe.add(((Pipe)sprites.get(i)).marshal());
			
			if(sprites.get(i).isGoomba())
				tmpListGoomba.add(((Goomba)sprites.get(i)).marshal());
		}	
		return ob;
	}
	
	void unmarshal(Json ob)
	{
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
		Json tmpListPipe = ob.get("pipes");
		Json tmpListGoomba = ob.get("goombas");
		for(int i = 0; i < tmpListPipe.size(); i++)
		{
			sprites.add(new Pipe(tmpListPipe.get(i)));
		}
		for(int i = 0; i < tmpListGoomba.size(); i++)
		{
			sprites.add(new Goomba(tmpListGoomba.get(i)));
		}
	}
}

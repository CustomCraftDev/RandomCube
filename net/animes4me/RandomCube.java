package net.animes4me;
	import java.util.Random;
	import org.bukkit.Location;
	import org.bukkit.Material;
	import org.bukkit.event.EventHandler;
	import org.bukkit.event.Listener;
	import org.bukkit.event.player.PlayerDropItemEvent;
	import org.bukkit.plugin.java.JavaPlugin;


	public class RandomCube extends JavaPlugin implements Listener {
		private Location[] now = new Location[3];
		private boolean running = false;
		private Random rand;
				
		
		public void onEnable(){
			rand = new Random();
			now[2] = new Location(getServer().getWorlds().get(0), 0, 51, 0);
			getServer().getPluginManager().registerEvents(this, this);
			
			getServer().getScheduler().runTaskTimer(this, new Runnable(){
				@Override
				public void run() {
					if(running){
						next();
					}
				}		  
			}, 0, 1);
			
		}
		
		
		protected void next() {
			int next = rand.nextInt(6) + 1;

			for(Location temp : now){
				if(temp != null){
					temp.getBlock().setType(Material.QUARTZ_BLOCK);
				}
			}
			
			if(next == 1){
				if(now[2].getBlockX() < 50){
					now[0] = now[2].clone().add(1, 0, 0);
					now[1] = now[2].clone().add(2, 0, 0);
					now[2] = now[2].clone().add(3, 0, 0);
				}
			}else if(next == 2){
				if(now[2].getBlockX() > -50){
					now[0] = now[2].clone().add(-1, 0, 0);
					now[1] = now[2].clone().add(-2, 0, 0);
					now[2] = now[2].clone().add(-3, 0, 0);
				}
			}else if(next == 3){
				if(now[2].getBlockY() < 100){
					now[0] = now[2].clone().add(0, 1, 0);
					now[1] = now[2].clone().add(0, 2, 0);
					now[2] = now[2].clone().add(0, 3, 0);
				}
			}else if(next == 4){
				if(now[2].getBlockY() > 0){
					now[0] = now[2].clone().add(0, -1, 0);
					now[1] = now[2].clone().add(0, -2, 0);
					now[2] = now[2].clone().add(0, -3, 0);
				}
			}else if(next == 5){
				if(now[2].getBlockZ() < 50){
					now[0] = now[2].clone().add(0, 0, 1);
					now[1] = now[2].clone().add(0, 0, 2);
					now[2] = now[2].clone().add(0, 0, 3);
				}
			}else if(next == 6){
				if(now[2].getBlockZ() > -50){
					now[0] = now[2].clone().add(0, 0, -1);
					now[1] = now[2].clone().add(0, 0, -2);
					now[2] = now[2].clone().add(0, 0, -3);
				}
			}
			
			now[2].getBlock().setType(Material.GOLD_BLOCK);
		}

		
		@EventHandler
		public void onInteract(PlayerDropItemEvent e){
			if(running){
				getServer().broadcastMessage("paused");
				now[2].getBlock().setType(Material.AIR);
			}else{
				getServer().broadcastMessage("running");
			}
			running = !running;
		}
		
		
	}

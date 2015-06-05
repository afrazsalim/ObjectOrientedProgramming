package jumpingalien.part3.Expressions;

import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.WorldObjects;

public class NearestObject {

	
	private double distance;

	public  WorldObjects get(Program program,WorldObjects Object) {
       WorldObjects nearestPlant;
       double distanceToNearst = 0;
       double x;
       double y;
       double plantX;
       double plantY;
	   double distance = 0;
		   x = program.getObject().getPixelLeftX();
		   y = program.getObject().getPixelBottomY();
		   nearestPlant = null;
		  if(program.getObject() instanceof Plant){
		   for(Plant plant:program.getObject().getWorld().getCollectionPlant()){
			   if(plant.getHitPoints() > 0){
				   plantX = plant.getPixelLeftX();
				   plantY = plant.getPixelBottomY();
				   distance = (((x-plantX)*(x-plantX))+ ((y-plantY)*(y-plantY)));
				   if(nearestPlant == null)
					   nearestPlant = plant;
				   distanceToNearst = distance;
			   }
			   else if(distance < distanceToNearst){
				   nearestPlant = plant;
				   distanceToNearst = distance;
			   }
		   }
		   this.setNearetsDistance(distanceToNearst);
		   } else if(program.getObject() instanceof Slime){
			   for(Slime slime:program.getObject().getWorld().getCollectionOfSlimes()){
				   if(slime.getHitPoints() > 0){
					   plantX = slime.getPixelLeftX();
					   plantY = slime.getPixelBottomY();
					   distance = (((x-plantX)*(x-plantX))+ ((y-plantY)*(y-plantY)));
					   if(nearestPlant == null)
						   nearestPlant = slime;
					   distanceToNearst = distance;
				   }
				   else if(distance < distanceToNearst){
					   nearestPlant = slime;
					   distanceToNearst = distance;
				   }
			   }
			   this.setNearetsDistance(distanceToNearst);
			   }
		   else if(program.getObject() instanceof Shark){
			   for(Shark shark:program.getObject().getWorld().getCollectionOfSharks()){
				   if(shark.getHitPoints() > 0){
					   plantX = shark.getPixelLeftX();
					   plantY = shark.getPixelBottomY();
					   distance = (((x-plantX)*(x-plantX))+ ((y-plantY)*(y-plantY)));
					   if(nearestPlant == null)
						   nearestPlant = shark;
					   distanceToNearst = distance;
				   }
				   else if(distance < distanceToNearst){
					   nearestPlant = shark;
					   distanceToNearst = distance;
				   }
			   }
			   this.setNearetsDistance(distanceToNearst);
			   }
		//  System.out.println("plant" + nearestPlant);
	 return nearestPlant;
	}
	
	

	
	private void setNearetsDistance(double dist) {
		 this.distance = dist;
	}
	

	
	public double getDistance(){
		return this.distance;
	}

	

}

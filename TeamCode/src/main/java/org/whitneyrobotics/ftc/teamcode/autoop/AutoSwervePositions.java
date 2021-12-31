package org.whitneyrobotics.ftc.teamcode.autoop;

import org.whitneyrobotics.ftc.teamcode.lib.geometry.Position;
import org.whitneyrobotics.ftc.teamcode.lib.purepursuit.FollowerConstants;
import org.whitneyrobotics.ftc.teamcode.lib.purepursuit.swervetotarget.SwervePath;
import org.whitneyrobotics.ftc.teamcode.lib.purepursuit.swervetotarget.SwervePathGenerationConstants;

import java.util.ArrayList;

import static org.whitneyrobotics.ftc.teamcode.lib.purepursuit.PathGenerator.generateSwervePath;

import com.acmerobotics.dashboard.config.Config;

@Config
public class AutoSwervePositions {

 //carousel path for red top AND bottom
 public final static Position redCarousel1 = new Position(-1219.2,-609.6);
 public final static Position redCarousel2 = new Position(-1219.2,774.7);
 public final static Position redCarousel3 = new Position(-1400.2,1329.2);

 /*
 cursed
 public final static Position blueCarousel1 = new Position(-1219.2,609.6);
 public final static Position blueCarousel2 = new Position(-1219.2,-774.7);
 public final static Position blueCarousel3 = new Position(-1400.2,-1329.2);
 */

//Start to Carousel
 public final  static Position startToCarousel1 = new Position(-900, -1800);
 public final  static Position startToCarousel2 = new Position(-1800, -1800);
 //Carousel to Shipping Hub
 public final  static Position carouselToShippingHub1 = new Position(-1800, -1800);
 public final  static Position carouselToShippingHub2 = new Position(-300, -900);
 //Shipping Hub to Warehouse
 public final  static Position  shippingHubToWarehouse1 = new Position(-300, -900);
 public final  static Position  shippingHubToWarehouse2 = new Position(600, -1500);
//Warehouse to Shipping Hub
 public final  static Position  warehouseToShippingHub1= new Position(600, -1500);
 public final  static Position  warehouseToShippingHub2 = new Position(-300, -900);
 //Shipping Hub to Warehouse (Park)
 public final  static Position shippingHubToWarehousePark1 = new Position(-300, -900);
 public final  static Position shippingHubToWarehousePark2 = new Position(900, -900);
 //Shipping Hub to Storage Unit
 public final  static Position shippingHubToStorageUnit1 = new Position(-300, -900);
 public final  static Position shippingHubToStorageUnit2 = new Position(-1500, -900);

 ArrayList<Integer> sus = new ArrayList<Integer>();

 //swerve lookahead distances
 public static double startToCarouselLookaheadDistance = 350; // in mm
 public static double carouselToShippingHubLookaheadDistance = 350;
 public static double shippingHubToWarehouseLookaheadDistance = 350;
 public static double warehouseToShippingHubLookaheadDistance = 350;
 public static double shippingHubToWarehouseParkLookaheadDistance = 350;
 public static double shippingHubToStorageUnitLookaheadDistance = 350;
 public static double crossIntoRedWarehouseLookaheadDistance = 350;
 public static double crossFromRedWarehouseLookaheadDistance = 350;
 public static double crossIntoBlueWarehouseLookaheadDistance = 350;
 public static double crossFromBlueWarehouseLookaheadDistance = 350;

 //swerve spacing
 public static double startToCarouselSwerveSpacing =80;
 public static double  carouselToShippingHubSwerveSpacing =80;
 public static double shippingHubToWarehouseSwerveSpacing =80;
 public static double warehouseToShippingHubSwerveSpacing =80;
 public static double shippingHubToWarehouseParkSwerveSpacing =80;
 public static double shippingHubToStorageUnitSwerveSpacing =80;
 public static double crossIntoRedWarehouseServeSpacing = 20;
 public static double crossFromRedWarehouseSwerveSpacing = 20;
 public static double crossIntoBlueWarehouseSwerveSpacing = 20;
 public static double crossFromBlueWarehouseSwerveSpacing = 20;

 //Weight Smooth
 public static double startToCarouselWeightSmooth = 0.5;
 public static double carouselToShippingHubWeightSmooth = 0.5;
 public static double shippingHubToWarehouseWeightSmooth = 0.5;
 public static double warehouseToShippingHubWeightSmooth = 0.5;
 public static double shippingHubToWarehouseParkWeightSmooth = 0.5;
 public static double shippingHubToStorageUnitWeightSmooth = 0.5;
 public static double crossIntoRedWarehouseSwerveWeightSmooth = 0.7;
 public static double crossFromRedWarehouseSwerveWeightSmooth = 0.7;
 public static double crossIntoBlueWarehouseSwerveWeightSmooth = 0.7;
 public static double crossFromBlueWarehouseSwerveWeightSmooth = 0.7;

 //swerve turn speed (1-5)
 public static double startToCarouselTurnSpeed = 3;
 public static double carouselToShippingHubTurnSpeed = 3;
 public static double shippingHubToWarehouseTurnSpeed = 3;
 public static double warehouseToShippingHubTurnSpeed = 3;
 public static double shippingHubToWarehouseParkTurnSpeed = 3;
 public static double shippingHubToStorageUnitTurnSpeed = 3;
 public static double crossIntoRedWarehouseTurnSpeed = 1.5;
 public static double crossFromRedWarehouseTurnSpeed = 1.5;
 public static double crossIntoBlueWarehouseTurnSpeed = 1.5;
 public static double crossFromBlueWarehouseTurnSpeed = 1.5;


 //swerve max velocity
 public static double startToCarouselMaxVelocity = 750;
 public static double carouselToShippingHubMaxVelocity = 750;
 public static double shippingHubToWarehouseMaxVelocity = 750;
 public static double warehouseToShippingHubMaxVelocity = 750;
 public static double shippingHubToWarehouseParkMaxVelocity = 750;
 public static double shippingHubToStorageUnitMaxVelocity = 750;
 public static double crossIntoRedWarehouseMaxVelocity = 400;
 public static double crossFromRedWarehouseMaxVelocity = 400;
 public static double crossIntoBlueWarehouseMaxVelocity = 400;
 public static double crossFromBlueWarehouseMaxVelocity = 400;

 //ArrayLists to call in getPath
 public static ArrayList<Position> startToCarouselPath = new ArrayList<Position>();
 public static ArrayList<Position> carouselToShippingHubPath = new ArrayList<Position>();
 public static ArrayList<Position> shippingHubToWarehousePath = new ArrayList<Position>();
 public static ArrayList<Position> warehouseToShippingHubPath = new ArrayList<Position>();
 public static ArrayList<Position> shippingHubToWarehouseParkPath = new ArrayList<Position>();
 public static ArrayList<Position> shippingHubToStorageUnitPath = new ArrayList<Position>();
 public static ArrayList<Position> crossIntoRedWarehousePath = new ArrayList<>();
 public static ArrayList<Position> crossFromRedWarehousePath = new ArrayList<>();
 public static ArrayList<Position> crossIntoBlueWarehousePath = new ArrayList<>();
 public static ArrayList<Position> crossFromBlueWarehousePath = new ArrayList<>();

 //Initialioze Follower Constants
 public static FollowerConstants startToCarouselFollowerConstants = new FollowerConstants(startToCarouselLookaheadDistance, false);
 public static FollowerConstants carouselToShippingHubFollowerConstants = new FollowerConstants(carouselToShippingHubLookaheadDistance, false);
 public static FollowerConstants shippingHubToWarehouseFollowerConstants = new FollowerConstants(shippingHubToWarehouseLookaheadDistance, false);
 public static FollowerConstants warehouseToShippingHubFollowerConstants = new FollowerConstants(warehouseToShippingHubLookaheadDistance, false);
 public static FollowerConstants shippingHubToWarehouseParkFollowerConstants = new FollowerConstants(shippingHubToWarehouseParkLookaheadDistance, false);
 public static FollowerConstants shippingHubToStorageUnitFollowerConstants = new FollowerConstants(shippingHubToStorageUnitLookaheadDistance, false);
 public static FollowerConstants crossIntoRedWarehouseFollowerConstants = new FollowerConstants(crossIntoRedWarehouseLookaheadDistance,false);
 public static FollowerConstants crossFromRedWarehouseFollowerConstants = new FollowerConstants(crossIntoRedWarehouseLookaheadDistance,true);
 public static FollowerConstants crossIntoBlueWarehouseFollowerConstants = new FollowerConstants(crossIntoBlueWarehouseLookaheadDistance,false);
 public static FollowerConstants crossFromBlueWarehouseFollowerConstants = new FollowerConstants(crossFromBlueWarehouseLookaheadDistance,true);

 public static SwervePathGenerationConstants crossIntoRedWarehousePathGenerationConstants = new SwervePathGenerationConstants(AutoSwervePositions.crossIntoRedWarehouseServeSpacing,AutoSwervePositions.crossIntoRedWarehouseSwerveWeightSmooth,AutoSwervePositions.crossIntoRedWarehouseTurnSpeed,AutoSwervePositions.crossIntoRedWarehouseMaxVelocity);
 public static SwervePathGenerationConstants crossFromRedWarehousePathGenerationConstants = new SwervePathGenerationConstants(crossFromRedWarehouseSwerveSpacing,crossFromRedWarehouseSwerveWeightSmooth,crossFromRedWarehouseTurnSpeed,crossFromRedWarehouseMaxVelocity);
 public static SwervePathGenerationConstants crossIntoBlueWarehousePathGenerationConstants = new SwervePathGenerationConstants(crossIntoBlueWarehouseSwerveSpacing,crossIntoBlueWarehouseSwerveWeightSmooth,crossIntoBlueWarehouseTurnSpeed,crossIntoBlueWarehouseMaxVelocity);
 public static SwervePathGenerationConstants crossFromBlueWarehousePathGenerationConstants = new SwervePathGenerationConstants(crossFromBlueWarehouseSwerveSpacing,crossFromBlueWarehouseSwerveWeightSmooth,crossFromBlueWarehouseTurnSpeed,crossFromBlueWarehouseMaxVelocity);
 public static SwervePath crossIntoRedWarehouse;
 public static SwervePath crossFromRedWarehouse;
 public static SwervePath crossIntoBlueWarehouse;
 public static SwervePath crossFromBlueWarehouse;

 public static ArrayList<Position> getPath(ArrayList<Position> pathArray){
  crossIntoRedWarehousePath.add(crossIntoWarehouseRed1);
  crossIntoRedWarehousePath.add(crossIntoWarehouseRed2);
  crossIntoRedWarehousePath.add(crossIntoWarehouseRed3);
  crossIntoRedWarehousePath.add(crossIntoWarehouseRed4);

  crossFromRedWarehousePath.add(crossFromWarehouseRed1);
  crossIntoRedWarehousePath.add(crossFromWarehouseRed2);
  crossIntoRedWarehousePath.add(crossFromWarehouseRed3);
  crossIntoRedWarehousePath.add(crossFromWarehouseRed4);

  crossIntoBlueWarehousePath.add(crossIntoWarehouseBlue1);
  crossIntoBlueWarehousePath.add(crossIntoWarehouseBlue2);
  crossIntoBlueWarehousePath.add(crossIntoWarehouseBlue3);
  crossIntoBlueWarehousePath.add(crossIntoWarehouseBlue4);

  crossFromBlueWarehousePath.add(crossFromWarehouseBlue1);
  crossIntoBlueWarehousePath.add(crossFromWarehouseBlue2);
  crossIntoBlueWarehousePath.add(crossFromWarehouseBlue3);
  crossIntoBlueWarehousePath.add(crossFromWarehouseBlue4);
  return pathArray;
 }

 public static void generateAutoPaths(){
//
  crossIntoRedWarehouse = generateSwervePath(crossIntoRedWarehousePath,crossIntoRedWarehouseFollowerConstants,crossIntoRedWarehousePathGenerationConstants);
  crossFromRedWarehouse = generateSwervePath(crossFromRedWarehousePath,crossFromRedWarehouseFollowerConstants,crossFromRedWarehousePathGenerationConstants);

  crossIntoBlueWarehouse = generateSwervePath(crossIntoBlueWarehousePath,crossIntoBlueWarehouseFollowerConstants,crossIntoBlueWarehousePathGenerationConstants);
  crossFromBlueWarehouse = generateSwervePath(crossFromBlueWarehousePath,crossFromBlueWarehouseFollowerConstants,crossFromBlueWarehousePathGenerationConstants);
 }
}



// package backend.world;

// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.List;

// import backend.world.State.Game;
// import eg.edu.alexu.csd.oop.game.GameObject;

// public class Memento {
//     private final int score;
//     private final HeartCounter heartCounter;
//     private final List<GameObject> constant;
//     private final List<GameObject> moving;
//     private final List<GameObject> control;
//     private final List<GameObject> objectsToFall;
//     private int i;

//     private long timePassedInms;

//     public List<GameObject> getObjectsToFall() {
//         return this.objectsToFall;
//     }


//     public int getI() {
//         return this.i;
//     }

//     public void setI(int i) {
//         this.i = i;
//     }

//     public long getTimePassedInms() {
//         return this.timePassedInms;
//     }

//     public void setTimePassedInms(long timePassedInms) {
//         this.timePassedInms = timePassedInms;
//     }


//     public Memento(int i,int score, HeartCounter heartCounter, List<GameObject> objToFall,List<GameObject> constant, List <GameObject> moving, List<GameObject> control){
//         this.score=score;
//         this.heartCounter=new HeartCounter(heartCounter.getLives());
//         this.constant= createList(constant);
//         this.moving= createList(moving);
//         this.control= createList(control);
//         this.objectsToFall = createList(objToFall);
//         this.i = i;
//     }

//     private List<GameObject> createList(List<GameObject> list)
//     {
//         List<GameObject> newList = new LinkedList<GameObject>();
//         newList.addAll(list);
//         return newList;
//     }
//     public List<GameObject> getConstant() {
//         return constant;
//     }

//     public List<GameObject> getControl() {
//         return control;
//     }

//     public HeartCounter getHeartCounter() {
//         return heartCounter;
//     }

//     public List<GameObject> getMoving() {
//         return moving;
//     }

//     public int getScore() {
//         return score;
//     }
// }

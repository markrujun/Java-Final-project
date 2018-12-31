//package logic.battle;
//
//import logic.creature.Creature;
//import logic.creature.leader;
//import logic.position.Position;
//
//import java.util.Scanner;
//
//public class Formation {
//    public Creature[] evilCreatures;
//    public logic.creature.leader leader;
//    public Position[][] positions;
//    private Boolean flipping;
//    Formation(Creature[] evilCreatures, leader leader, Position[][] positions, Boolean flipping){
//        this.leader = leader;
//        this.evilCreatures = evilCreatures;
//        this.positions = positions;
//        this.flipping = flipping;
//    }
//
//    private void snake(){
//        Position tempPosition = new Position(1, 1, null);
//        if(positions.length<evilCreatures.length || positions.length<4){
//            System.out.println("战场太小啦！请重新创建！");
//            System.exit(0);
//        }else{
//            if(!flipping){
//                tempPosition = new Position((positions.length-evilCreatures.length)/2, (positions.length-1)/2-1, null );
//                creatureBindPosition(leader, positions[positions.length/2][0]);
//            }else{
//                tempPosition = new Position((positions.length-evilCreatures.length)/2, (positions.length-1)/2+1, null);
//                creatureBindPosition(leader, positions[positions.length/2][positions.length-1]);
//            }
//        }
//        for(Creature creature:this.evilCreatures){
//            creatureBindPosition(creature,positions[tempPosition.x][tempPosition.y]);
//            tempPosition.x++;
//        }
//    }
//
//    public void reuilly(){
//        Position tempPosition = new Position(1, 1, null);
//        if(positions.length<evilCreatures.length*2+1){
//            System.out.println("来个大点的战场吧！");
//            System.exit(0);
//        }else{
//            if(!flipping){
//                tempPosition = new Position((positions.length-evilCreatures.length)/2,positions.length/2-1,null);
//            }else{
//                tempPosition = new Position((positions.length-evilCreatures.length)/2,positions.length/2+1,null);
//            }
//        }
//        if(flipping){
//            for(int i=0;i<evilCreatures.length;i++){
//                creatureBindPosition(evilCreatures[i],positions[tempPosition.x+evilCreatures.length/2-Math.abs(evilCreatures.length/2-i)][tempPosition.y+i]);
//            }
//        }else {
//            for(int i=0;i<evilCreatures.length;i++){
//                creatureBindPosition(evilCreatures[i],positions[tempPosition.x+evilCreatures.length/2-Math.abs(evilCreatures.length/2-i)][tempPosition.y-i]);
//            }
//        }
//    }
//    public void goose(){
//        Position tempPosition = new Position(1, 1, null);
//        if(positions.length<evilCreatures.length*2+1){
//            System.out.println("来个大点的战场吧！");
//            System.exit(0);
//        }else{
//            if(!flipping){
//                tempPosition = new Position((positions.length-evilCreatures.length)/2,positions.length/2,null);
//            }else{
//                tempPosition = new Position((positions.length-evilCreatures.length)/2,positions.length/2,null);
//            }
//        }
//        if(flipping){
//            for(int i=0;i<evilCreatures.length;i++){
//                creatureBindPosition(evilCreatures[i],positions[tempPosition.x+i][tempPosition.y+i]);
//            }
//        }else {
//            for(int i=0;i<evilCreatures.length;i++){
//                creatureBindPosition(evilCreatures[i],positions[tempPosition.x+i][tempPosition.y-i]);
//            }
//        }
//    }
//    public void creatureBindPosition(Creature creature, Position position){
//        if(creature.position.creature == creature){
//            creature.position.creature = null;
//        }
//        position.creature =  creature;
//        creature.position = position;
//    }
//    public void changeFormation(String formation){
//        if(formation == null){
//            System.out.println("请输入数字以选择要使用的阵型：");
//            System.out.println("1.蛇阵");
//            System.out.println("2.仙鹤阵");
//            System.out.println("3.麻雀阵");
//            Scanner sc = new Scanner(System.in);
//            String formationNumber = sc.next();
//            switch (formationNumber){
//                case "1":
//                    this.snake();
//                    break;
//                case "2":
//                    this.reuilly();
//                    break;
//                case "3":
//                    this.goose();
//                    break;
//                default:
//                    System.out.println("对不起，无此阵。");
//            }
//        }else{
//            switch (formation){
//                case "snake":
//                    this.snake();
//                    break;
//                case "2":
//                    this.reuilly();
//                    break;
//                case "3":
//                    this.goose();
//                    break;
//                default:
//                    System.out.println("对不起，无此阵。");
//            }
//        }
//    }
//}

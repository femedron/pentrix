package com.pentrix.game;

public class PatternGenerator {
    public static int[][] get(int seed, int brickCount){
        Point[] p = getPoints(seed);
        if(p == null)
            return null;
        int[][] ret = new int[5][5];
        for(int i = 0; i< brickCount; i++){
            Point point = p[i];
            ret[point.y-1][point.x-1] = 1;
        }
        return ret;
    }

    private static Point[] getPoints(int seed){
        switch (seed){
            case 1: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4),new Point(3,5)};
            case 2: return new Point[]{new Point(3,2),new Point(4,2),new Point(2,3),new Point(3,3),new Point(3,4)};
            case 3: return new Point[]{new Point(3,2),new Point(4,2),new Point(4,3),new Point(3,3),new Point(2,3)};
            case 4: return new Point[]{new Point(3,3),new Point(2,2),new Point(2,3),new Point(3,2),new Point(4,3)};
            case 5: return new Point[]{new Point(2,2),new Point(2,3),new Point(3,2),new Point(4,2),new Point(4,3)};
            case 6: return new Point[]{new Point(2,2),new Point(2,3),new Point(3,3),new Point(3,4),new Point(4,4)};
            case 7: return new Point[]{new Point(2,3),new Point(3,3),new Point(4,3),new Point(3,2),new Point(3,4)};
            case 8: return new Point[]{new Point(2,3),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,3)};
            case 9: return new Point[]{new Point(3,2),new Point(3,3),new Point(4,3),new Point(4,4),new Point(3,4)};
            case 10: return new Point[]{new Point(2,2),new Point(3,2),new Point(3,3),new Point(4,3),new Point(4,4)};
            case 11: return new Point[]{new Point(3,2),new Point(4,2),new Point(2,3),new Point(3,3),new Point(3,4)};
            case 12: return new Point[]{new Point(2,3),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,3)};
            case 13: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3),new Point(4,3),new Point(5,3)};
            case 14: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3),new Point(4,3),new Point(4,4)};
            case 15: return new Point[]{new Point(2,2),new Point(3,2),new Point(3,3),new Point(4,3),new Point(4,4)};
            case 16: return new Point[]{new Point(2,3),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,3)};
            case 17: return new Point[]{new Point(3,2),new Point(3,3),new Point(4,3),new Point(4,4),new Point(3,4)};
            case 18: return new Point[]{new Point(2,3),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,3)};
            default: return null;
        }
    }
}

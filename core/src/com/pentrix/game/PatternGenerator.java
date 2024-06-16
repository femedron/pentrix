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
            // 1 block
            case 1: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4),new Point(3,5)};
//            case 2: return new Point[]{new Point(1,3), new Point(1,2), new Point(1,3), new Point(1,4), new Point(1,5)};
//            case 3: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4),new Point(3,5)};
//            case 4: return new Point[]{new Point(1,3), new Point(1,2), new Point(1,3), new Point(1,4), new Point(1,5)};

            // 2 block 1v
            case 2: return new Point[]{new Point(3,2),new Point(4,3),new Point(3,3),new Point(2,3),new Point(2,4)};
//            case 6: return new Point[]{new Point(2,2),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,3)};
//            case 7: return new Point[]{new Point(2,3),new Point(3,3),new Point(3,4),new Point(4,4),new Point(4,2)};
//            case 8: return new Point[]{new Point(2,3),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,4)};

            // 2 block 2v
            case 3: return new Point[]{new Point(3,2),new Point(4,2),new Point(2,3),new Point(3,3),new Point(3,4)};
//            case 10: return new Point[]{new Point(2,2),new Point(2,3), new Point(3,3),new Point(3,4),new Point(4,3)};
//            case 11: return new Point[]{new Point(3,2), new Point(3,3),new Point(3,4), new Point(2,4), new Point(4,3)};
//            case 12: return new Point[]{new Point(3,2),new Point(3,3),new Point(2,3), new Point(4,4), new Point(4,3)};


            // 3 block 1v
//            case 13: return new Point[]{new Point(1,3),new Point(2,3),new Point(3,3),new Point(4,3), new Point(4,2)};
//            case 14: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3), new Point(3,4), new Point(4,4)};
//            case 15: return new Point[]{new Point(1,3),new Point(1,4),new Point(2,3), new Point(3,3), new Point(4,3)};
            case 4: return new Point[]{new Point(2,1),new Point(3,1),new Point(3,2), new Point(3,3), new Point(3,4)};


            // 3 block 2v
//            case 17: return new Point[]{new Point(1,3),new Point(2,3),new Point(3,3),new Point(4,3), new Point(4,4)};
//            case 18: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3), new Point(3,4), new Point(2,4)};
//            case 19: return new Point[]{new Point(1,3),new Point(1,2),new Point(2,3), new Point(3,3), new Point(4,3)};
            case 5: return new Point[]{new Point(4,1),new Point(3,1),new Point(3,2), new Point(3,3), new Point(3,4)};


            // 4 block 1v
//            case 21: return new Point[]{new Point(2,2),new Point(2,3),new Point(3,2), new Point(3,3), new Point(4,3)};
//            case 22: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4), new Point(4,2),new Point(4,3)};
//            case 23: return new Point[]{new Point(2,2),new Point(3,2),new Point(4,2), new Point(3,3), new Point(4,3)};
            case 6: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4), new Point(2,3), new Point(2,4)};


            // 4 block 2v
//            case 25: return new Point[]{new Point(2,2),new Point(3,2),new Point(4,2), new Point(2,3), new Point(3,3)};
//            case 26: return new Point[]{new Point(2,2),new Point(2,3),new Point(3,2), new Point(3,3), new Point(3,4)};
//            case 27: return new Point[]{new Point(2,3),new Point(3,3),new Point(4,3), new Point(3,2), new Point(4,2)};
            case 7: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4), new Point(4,3), new Point(4,4)};


            // 5 block 1v
//            case 29: return new Point[]{new Point(2,3),new Point(3,3),new Point(4,3), new Point(4,2), new Point(5,2)};
//            case 30: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4), new Point(4,4), new Point(4,5)};
//            case 31: return new Point[]{new Point(1,4),new Point(2,4),new Point(2,3), new Point(3,3), new Point(4,3)};
            case 8: return new Point[]{new Point(2,1),new Point(2,2),new Point(3,2), new Point(3,3), new Point(3,4)};


            // 5 block 2v
//            case 33: return new Point[]{new Point(2,3),new Point(3,3),new Point(4,3), new Point(4,4), new Point(5,4)};
//            case 34: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4), new Point(2,4), new Point(2,5)};
//            case 35: return new Point[]{new Point(1,2),new Point(2,2),new Point(2,3), new Point(3,3), new Point(3,4)};
            case 9: return new Point[]{new Point(4,1),new Point(4,2),new Point(3,2), new Point(3,3), new Point(3,4)};


            // 6 block
//            case 37: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,3),new Point(5,3)};
//            case 38: return new Point[]{new Point(2,3),new Point(3,3),new Point(4,3),new Point(3,4),new Point(3,5)};
//            case 39: return new Point[]{new Point(3,2), new Point(3,3),new Point(3,4),new Point(2,3),new Point(1,3)};
            case 10: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3),new Point(2,3),new Point(4,3)};


            // 7 block
            case 11: return new Point[]{new Point(3,2),new Point(2,2),new Point(3,3),new Point(3,4),new Point(2,4)};
//            case 42: return new Point[]{new Point(2,4),new Point(2,3),new Point(3,3),new Point(4,3),new Point(4,4)};
//            case 43: return new Point[]{new Point(4,2),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,4)};
//            case 44: return new Point[]{new Point(2,2),new Point(2,3),new Point(3,3),new Point(4,3),new Point(4,2)};


            // 8 block
//            case 45: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,4),new Point(1,4)};
//            case 46: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,4),new Point(5,4)};
//            case 47: return new Point[]{new Point(5,2),new Point(4,2),new Point(3,2),new Point(3,3),new Point(3,4)};
            case 12: return new Point[]{new Point(1,2),new Point(2,2),new Point(3,2),new Point(3,3),new Point(3,4)};


            // 9 block
//            case 49: return new Point[]{new Point(4,2),new Point(4,3),new Point(3,3),new Point(3,4),new Point(2,4)};
//            case 50: return new Point[]{new Point(2,2),new Point(2,3),new Point(3,3),new Point(3,4),new Point(4,4)};
//            case 51: return new Point[]{new Point(4,2),new Point(3,2),new Point(3,3),new Point(2,3),new Point(2,4)};
            case 13: return new Point[]{new Point(2,2),new Point(3,2),new Point(3,3),new Point(4,3),new Point(4,4)};


            // 10 block
//            case 53: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,3),new Point(4,3)};
//            case 54: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,3),new Point(4,3)};
//            case 55: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,3),new Point(4,3)};
            case 14: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,3),new Point(4,3)};


            // 11 block v1
//            case 57: return new Point[]{new Point(3,2),new Point(3,3),new Point(4,3),new Point(2,3),new Point(5,3)};
//            case 58: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(3,5),new Point(4,3)};
//            case 59: return new Point[]{new Point(1,3),new Point(2,3),new Point(3,3),new Point(3,4),new Point(4,3)};
            case 15: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,3)};


            // 11 block v2
//            case 61: return new Point[]{new Point(2,3),new Point(3,3),new Point(3,4),new Point(4,3),new Point(5,3)};
//            case 62: return new Point[]{new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,3),new Point(3,5)};
//            case 63: return new Point[]{new Point(1,3),new Point(2,3),new Point(3,3),new Point(3,2),new Point(4,3)};
            case 16: return new Point[]{new Point(3,1),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,3)};


            // 12 block v1
//            case 65: return new Point[]{new Point(2,3),new Point(2,4),new Point(3,3),new Point(4,2),new Point(4,3)};
//            case 66: return new Point[]{new Point(2,2),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,4)};
//            case 67: return new Point[]{new Point(2,3),new Point(2,4),new Point(3,3),new Point(4,2),new Point(4,3)};
            case 17: return new Point[]{new Point(2,2),new Point(3,2),new Point(3,3),new Point(3,4),new Point(4,4)};


            // 12 block v2
//            case 69: return new Point[]{new Point(2,2),new Point(2,3),new Point(3,3),new Point(4,3),new Point(4,4)};
//            case 70: return new Point[]{new Point(4,2),new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,4)};
//            case 71: return new Point[]{new Point(2,2),new Point(2,3),new Point(3,3),new Point(4,3),new Point(4,4)};
            case 18: return new Point[]{new Point(4,2),new Point(3,2),new Point(3,3),new Point(3,4),new Point(2,4)};


            default: return null;
        }
    }
}

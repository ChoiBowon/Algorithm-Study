import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    //0세대면 1개, 1세대면 2개, 2세대면 3개씩 커브가 늘어남.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int x, y, d, g;
        Info[] infoarr = new Info[N];
        int[][] map = new int[101][101];
        int count = 0;

        //map 초기화
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                map[i][j] = 0;
            }
        }

        //좌표 받아오기
        for (int i = 0; i < N; i++) {
            String[] temp = sc.nextLine().split(" ");
            x = Integer.parseInt(temp[0]);
            y = Integer.parseInt(temp[1]);
            d = Integer.parseInt(temp[2]);
            g = Integer.parseInt(temp[3]);
            infoarr[i] = new Info(x, y, d, g);
        }
//        infoarr[0] = 3,3,0,1
//        infoarr[1] = 4,2,1,3
//        infoarr[2] = 4,2,2,1
        //d 는 시작방향
        //0세대면 1개, 1 세대면 커브가 2개 생기고, 2세대면 4개 3세대면 8개

        for (int i = 0; i < N; i++) {
            makeCurve(infoarr[i], map);
        }

        for (int i = 0; i < map.length-1; i++) {
            for (int j = 0; j < map.length - 1; j++) {
                if(map[i][j] == 1){
                    if(map[i+1][j] == 1 && map[i][j+1] ==1 && map[i+1][j+1] ==1){
                        count++;
                    }
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(count);





    }

    private static void makeCurve(Info info, int[][] map) {
        ArrayList<Integer> arr = new ArrayList<>();


        int generate = (int)Math.pow(2, info.g);  //2 개 커브

        arr.add(info.d); //arr = 0,
        drawCurve(arr, generate);

        //3,3 좌표 마크
        map[info.x][info.y] = 1;
        for (int i = 0; i < generate; i++) {
            if(arr.get(i) == 0) {
                map[info.x+1][info.y] = 1; //3,4 좌표 마크
                info.x = info.x+1;
            }
            if(arr.get(i) == 1){
                map[info.x][info.y-1] = 1;
                info.y = info.y-1;
            }
            if(arr.get(i) == 2) {
                map[info.x-1][info.y] = 1;
                info.x = info.x-1;

            }
            if(arr.get(i) == 3) {
                map[info.x][info.y+1] = 1;
                info.y = info.y+1;

            }
//            System.out.println(info.x + "," + info.y);


        }

    }

    private static void drawCurve(ArrayList<Integer> arr, int generate) {
        // arr.size == generate 될때까지 arr[processed]부터 두배길이만큼 첫 인덱스부터 보면서 그려주면됨
        int processed = arr.size();

        if(processed == generate){
            return;
        }

        for (int i = processed-1; i >= 0; --i) {
            arr.add((arr.get(i)+1)%4);
        }
        drawCurve(arr, generate);

    }


}

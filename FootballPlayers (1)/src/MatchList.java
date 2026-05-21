/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nguye
 */
public class MatchList {

    Match[] arr = new Match[100];

    int count = 0;

    // them tran dau
    void themMatch() {

        arr[count] = new Match();

        arr[count].nhapMatch();

        count++;

        System.out.println("Da them tran dau");
    }

    // xem lich su tran dau
    void xemMatchHistory() {

        if(count == 0) {

            System.out.println("Khong co tran dau nao");

        } else {

            for(int i = 0; i < count; i++) {

                arr[i].xuatMatch();
            }
        }
    }
}

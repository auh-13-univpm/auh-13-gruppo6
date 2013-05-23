package com.univpm1.firenzestreests.util;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class ArrayListWrapper<T> implements Parcelable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<T> lista;
	public ArrayListWrapper(ArrayList<T> list){
		this.lista = list;
	}
	public ArrayListWrapper(Parcel in){
		//this.lista = in.read
	}
	public ArrayList<T> getList(){
		return lista;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeList(lista);
	}
}
//public class Student implements Parcelable{
//     private String id;
//     private String name;
//     private String grade;
// // Parcelling part
//      public Student(Parcel in){
//         String[] data = new String[3];
//  
//         in.readStringArray(data);
//         this.id = data[0];
//         this.name = data[1];
//         this.grade = data[2];
//     }
//     public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//         public Student createFromParcel(Parcel in) {
//             return new Student(in); 
//         }
//  
//         public Student[] newArray(int size) {
//             return new Student[size];
//         }
//     };
// }
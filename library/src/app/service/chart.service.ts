import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http'
import{Observable} from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class ChartService {
  private ip = "http://192.168.145.100";
  private chartsUrl = this.ip +":8080/charts"

  constructor(
    private http:HttpClient
  ) { }
  getBookofMonth(id: number): Observable<any> {
    return this.http.get(`${this.chartsUrl}/${id}`);  
   
  }
  getAllMonth(): Observable<any> {
   return  this.http.get(`${this.chartsUrl}`) 
  }
}

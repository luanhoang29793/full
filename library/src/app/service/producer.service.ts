import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProducerService {
  private producerUrl ="http://localhost:8080/producer"

  
  constructor(
private http :HttpClient
  ) { }

 
  getProducer(id: number): Observable<any> {
    return this.http.get(`${this.producerUrl}/${id}`);  
   
  }

  createProducer(author: Object): Observable<Object> {
    return this.http.post(`${this.producerUrl}/create/`, author);
  }

  updateProducer(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.producerUrl}/${id}`, value);
  }

  deleteProducer(id: number): Observable<any> {
    return this.http.put(`${this.producerUrl}/remove/${id}`, { responseType: 'text' });
  }

  getProducerList(): Observable<any> {
   return  this.http.get(`${this.producerUrl}`) 
  }
}

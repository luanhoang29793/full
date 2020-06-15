import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http';
import{Observable}from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private ip = "http://192.168.145.100";

  private bookUrl = this.ip +":8080/book"

  
  constructor(
private http :HttpClient
  ) { }

 
  getBook(id: number): Observable<any> {
    return this.http.get(`${this.bookUrl}/${id}`);  
  }
 getBookByIdCategory(id:number):Observable<any>{
   return this.http.get(`${this.bookUrl}/bookbycategory/${id}`)
 }
  createBook(author: Object): Observable<Object> {
    return this.http.post(`${this.bookUrl}/create/`, author);
  }

  updateBook(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.bookUrl}/${id}`, value);
  }

  deleteBook(id: number): Observable<any> {
    return this.http.put(`${this.bookUrl}/remove/${id}`, { responseType: 'text' });
  }

  getBookList(): Observable<any> {
   return  this.http.get(`${this.bookUrl}`) 
  }
  getBookDesc(): Observable<any> {
    return this.http.get(`${this.bookUrl}/desc`);  
  }
  getBooktop6(): Observable<any> {
    return this.http.get(`${this.bookUrl}/top6`);  
  }
}

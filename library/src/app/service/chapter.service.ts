import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChapterService {
private ip ="http://192.168.145.100";
  private chapterUrl =this.ip + ":8080/chapter"

  
  constructor(
private http :HttpClient
  ) { }

 
  getChapter(id: number): Observable<any> {
    return this.http.get(`${this.chapterUrl}/${id }`);  
   
  }

  createChapter(author: Object): Observable<Object> {
    return this.http.post(`${this.chapterUrl}/create/`, author);
  }
  createChapterforBook(author: Object, id:number): Observable<Object> {
    return this.http.post(`${this.chapterUrl}/createforBook/${id}`, author);
  }

  updateChapter(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.chapterUrl}/${id}`, value);
  }

  deleteChapter(id: number): Observable<any> {
    return this.http.put(`${this.chapterUrl}/remove/${id}`, { responseType: 'text' });
  }

  getChapterList(): Observable<any> {
    return  this.http.get(`${this.chapterUrl}`) 
  }
  getChapterForBook(id: number): Observable<any>{
    return this.http.get(`${this.chapterUrl}/listBook/${id}`);
  }
  exportChapter(id:number): Observable<any>{
    
    return this.http.get(`${this.chapterUrl}/export/${id}`)
    
  }
}

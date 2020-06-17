import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private ip = "http://192.168.145.100";

  // private categoryUrl = this.ip+ ":8080/category"
  private categoryUrl = "http://localhost:8080/category"

  
  constructor(
private http :HttpClient
  ) { }

 
  getCategory(id: number): Observable<any> {
    return this.http.get(`${this.categoryUrl}/${id}`);  
   
  }

  createCategoryr(author: Object): Observable<Object> {
    return this.http.post(`${this.categoryUrl}/create/`, author);
  }

  updateCategory(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.categoryUrl}/${id}`, value);
  }

  deleteCategory(id: number): Observable<any> {
    return this.http.put(`${this.categoryUrl}/remove/${id}`, { responseType: 'text' });
  }

  getCategoryList(): Observable<any> {
    return  this.http.get(`${this.categoryUrl}`) 
  }
}


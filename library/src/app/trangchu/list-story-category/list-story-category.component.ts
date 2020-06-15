import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/model/category';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/service/book.service';
import { Observable } from 'rxjs';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-list-story-category',
  templateUrl: './list-story-category.component.html',
  styleUrls: ['./list-story-category.component.css']
})
export class ListStoryCategoryComponent implements OnInit {
  p:number=1
  id: number;
  book: Observable<Book[]>;
  category : Category = new Category;

  constructor(
    private bookService: BookService,
    private categoryService : CategoryService,
    private router :Router,
    private route :ActivatedRoute

  ) { }

  ngOnInit() {
    this.reload();
  }
reload(){
  this.id = this.route.snapshot.params['id'];
  this.categoryService.getCategory(this.id).subscribe(data => {
    console.log(data,"data");
    this.category = data;})
  console.log(this.id,"id")
 this.bookService.getBookByIdCategory(this.id)
 .subscribe(data => {
   console.log(data,"data");
   this.book = data;
 }, error => console.log(error,"loi"));
}

  listChapter(id: number){
    console.log(id);
    console.log("detail")
    this.router.navigate(['user/listChapter',id]);
  }
}

import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Chapter } from 'src/app/model/chapter';
import { ChapterService } from 'src/app/service/chapter.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/service/book.service';
import { Author } from 'src/app/model/author';
import { AuthorService } from 'src/app/service/author.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-chapter',
  templateUrl: './list-chapter.component.html',
  styleUrls: ['./list-chapter.component.css']
})
export class ListChapterComponent implements OnInit {
  chapter: Observable<Chapter[]>;
  id: number;
  book:Book= new Book;
  author: Author = new Author;
  constructor(
    private chapterService: ChapterService,
    private route :ActivatedRoute,
    private bookService : BookService,
    private router : Router
    
  ) { }

  ngOnInit() {
    this.reload()
  }
  reload(){
    
    console.log("danh sach list chap")
    this.id = this.route.snapshot.params['id'];
   this.chapter=this.chapterService.getChapterForBook(this.id);
   this.bookService.getBook(this.id)
   .subscribe(data => {
     console.log(data);
     this.book = data;
   }, error => console.log(error));

  }
  editChapter( id1: number){
    this.router.navigate(['admin/editChapter',id1]);
    console.log(this.id)

  }
  deleteChapter(id:number){
    this.chapterService.deleteChapter(id).subscribe((data) => {Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Delete successful',
      showConfirmButton: false,
      timer: 1500,
    });        
      this.reload();
  }), error => Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Delete Failure',
      showConfirmButton: false,
      timer: 1500
    });;



}
listStory(){
  this.router.navigate(['admin/listStory']);
}
Chapter(id:number){
  this.router.navigate(['user/Chapter',id]);
}
}

import { Component, OnInit } from '@angular/core';
import { Chapter } from 'src/app/model/chapter';
import { Observable } from 'rxjs';
import { ChapterService } from 'src/app/service/chapter.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/service/book.service';
import { Book } from 'src/app/model/book';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-chapter',
  templateUrl: './create-chapter.component.html',
  styleUrls: ['./create-chapter.component.css']
})
export class CreateChapterComponent implements OnInit {
  id: number;
  chapter: Chapter;
  book: Book = new Book;
  submitted = false;


  constructor(
    private chapterService: ChapterService,
    private route :ActivatedRoute,
    private router:Router,
    private bookService : BookService

  ) { }

  ngOnInit() {
    this.chapter = new Chapter();
    console.log("vao create for Book")
    this.id = this.route.snapshot.params['id'];
    this.bookService.getBook(this.id)
      .subscribe(data => {
        console.log(data)
        this.book = data;
      }, error => console.log(error));
  }
  newCategory(): void {
    this.submitted = false;
    this.chapter = new Chapter  ();
  }
  CreateChapter() {


  }
  save() {
    this.chapterService.createChapterforBook(this.chapter, this.id)
    .subscribe((data) => {Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Create successful',
      showConfirmButton: false,
      timer: 1500
      
    }); console.log(data);
    location.reload();
  }), error => Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: ' Create Failure',
      showConfirmButton: false,
      timer: 1500
    });
  }
    onSubmit() {
      this.submitted = true;
      this.save();         
    }   
    listStory(id){
      this.router.navigate(['admin/listChapter',id]);
    }
  

}

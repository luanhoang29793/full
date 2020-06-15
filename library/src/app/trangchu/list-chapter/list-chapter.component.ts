import { Component, OnInit } from '@angular/core';
import{Router, ActivatedRoute} from'@angular/router'
import { Observable } from 'rxjs';
import { Book } from 'src/app/model/book';
import { Chapter } from 'src/app/model/chapter';
import { ChapterService } from 'src/app/service/chapter.service';
import { BookService } from 'src/app/service/book.service';
import { Author } from 'src/app/model/author';
import { AuthorService } from 'src/app/service/author.service';

@Component({
  selector: 'app-list-chapter',
  templateUrl: './list-chapter.component.html',
  styleUrls: ['./list-chapter.component.css']
})
export class ListChapterComponent implements OnInit {
book : Book = new Book;
chapter:Observable<Chapter[]>;
id : number;

  constructor(
    private router :Router,
    private route :ActivatedRoute,
    private bookService: BookService,

    private chapterService: ChapterService,
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.chapter=this.chapterService.getChapterForBook(this.id)
    this.bookService.getBook(this.id)
      .subscribe(data => {
        console.log(data)
        this.book = data;
      }, error => console.log(error));
    
  };
  
 Chapter(id: number){
    console.log(id);
    this.router.navigate(['user/Chapter',id]);
  }
}

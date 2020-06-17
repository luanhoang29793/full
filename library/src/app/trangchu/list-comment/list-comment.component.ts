import { Component, OnInit } from '@angular/core';
import { CommentService } from 'src/app/service/comment.service';

@Component({
  selector: 'app-list-comment',
  templateUrl: './list-comment.component.html',
  styleUrls: ['./list-comment.component.css']
})
export class ListCommentComponent implements OnInit {
  comment :Comment
  constructor(
    private commentService : CommentService

  ) { }

  ngOnInit() {
    this.commentService.getCommentList()
    .subscribe(data => {
      console.log(data)
      this.comment = data;
    }, error => console.log(error));
  }

}

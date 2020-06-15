import { Component, OnInit } from '@angular/core';
import { Chapter } from 'src/app/model/chapter';
import { ActivatedRoute, Router } from '@angular/router';
import { ChapterService } from 'src/app/service/chapter.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-chapter',
  templateUrl: './edit-chapter.component.html',
  styleUrls: ['./edit-chapter.component.css']
})
export class EditChapterComponent implements OnInit {
  id: number;
  chapter: Chapter = new Chapter;
  constructor(
    private route :ActivatedRoute,
    private router: Router,
    private chapterService : ChapterService
  ) { }

  ngOnInit() {
    this.chapter = new Chapter;
    this.id = this.route.snapshot.params['id'];
    this.chapterService.getChapter(this.id).subscribe(data => {
      console.log(data)
      this.chapter = data;
    }, error => console.log(error));
  }
  updateChapter() {
    this.chapterService.updateChapter(this.id, this.chapter)
    .subscribe((data) => {Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Edit successful',
      showConfirmButton: false,
      timer: 1500
      
    });
  }), error => Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: ' Edit Failure',
      showConfirmButton: false,
      timer: 1500
    });
    this.chapter = new Chapter();
    this.ngOnInit();
  }
  onSubmit() {
    this.updateChapter();    
  }
  listChapter(id){
    this.router.navigate(['admin/listChapter',id]);
  }

}

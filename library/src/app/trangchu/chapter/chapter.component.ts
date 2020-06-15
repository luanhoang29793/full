import { Component, OnInit,ElementRef, ViewChild, ViewEncapsulation } from '@angular/core';
import * as jsPDF from 'jspdf';  
import { ActivatedRoute, Router } from '@angular/router';
import { ChapterService } from 'src/app/service/chapter.service';
import { Chapter } from 'src/app/model/chapter';
import {
  DocumentEditorComponent, EditorService, SelectionService, SfdtExportService, WordExportService
} from '@syncfusion/ej2-angular-documenteditor';


@Component({
  selector: 'app-chapter',
  templateUrl: `./chapter.component.html`
    ,styleUrls: ['./chapter.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [EditorService, SelectionService, SfdtExportService,WordExportService]

})
export class ChapterComponent implements OnInit {
  id: number;
  chapter : Chapter;
  @ViewChild('document_editor', { static: false }) document_editor: DocumentEditorComponent;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private chapterService: ChapterService,
  ) { }

  ngOnInit() {


  this.id = this.route.snapshot.params['id'];
  this.reload(this.id);
  }
  reload(id: number){
    this.chapter = new Chapter();

    this.chapterService.getChapter(id)
    .subscribe(data => {
      console.log(data)
      this.chapter = data;
    }, error => console.log(error));
  }
  saveAsDocx(){
    console.log("vao dowload");
    this.document_editor.save("text","Docx");
  }  
  Export2Doc(element, filename = ''){
    var preHtml = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='utf-8'><title>Export HTML To Doc</title></head><body>";
    var postHtml = "</body></html>";
    var html = preHtml+document.getElementById(element).innerHTML+postHtml;

    var blob = new Blob(['\ufeff', html], {
        type: 'application/msword'
    });
    
    // Specify link url
    var url = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(html);
    
    // Specify file name
    filename = filename?filename+'.doc':'document.doc';
    
    // Create download link element
    var downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob ){
        navigator.msSaveOrOpenBlob(blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = url;
        
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
    
    document.body.removeChild(downloadLink);
}
ExportChapter(id:number){
  console.log("vao export")
  console.log(id,"id")
  this.chapterService.exportChapter(id).subscribe(data => {
    console.log(data)
    this.chapter = data;
  }, error => console.log(error));
}
firstChapter(id1: number){
  this.id = id1-1;
  console.log(this.id,"id");

  this.reload(this.id)
  this.router.navigate(['user/Chapter',this.id]);

}
SecondChapter(id2: number){
  this.id = id2+1;
  console.log(id2,"id");
  this.router.navigate(['user/Chapter',id2]);
  this.reload(this.id)


}
  }



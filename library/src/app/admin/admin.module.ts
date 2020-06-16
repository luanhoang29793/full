import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'
import { AdminComponent } from './admin/admin.component';
import { CreateSComponent } from './create-s/create-s.component';
import { ListSComponent } from './list-s/list-s.component';
import { CreatelistAComponent } from './createlist-a/createlist-a.component';
import { CreatelistCComponent } from './createlist-c/createlist-c.component';
import { CreateCComponent } from './create-c/create-c.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CreateAComponent } from './create-a/create-a.component';
import { CreateChapterComponent } from './create-chapter/create-chapter.component';
import { ListChapterComponent } from './list-chapter/list-chapter.component';
import { EditSComponent } from './edit-s/edit-s.component';
import { EditChapterComponent } from './edit-chapter/edit-chapter.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { DataTablesModule } from 'angular-datatables';
import { FlxUiDatatableModule, FlxUiDataTable } from 'flx-ui-datatable';
import { Ng2SearchPipeModule } from 'ng2-search-filter'
import { EditCComponent } from './edit-c/edit-c.component';
import { EditAComponent } from './edit-a/edit-a.component';
import { UploadImageComponent } from './upload-image/upload-image.component';
import { CKEditorModule } from 'ngx-ckeditor';
import { ChartsModule } from 'ng2-charts';
import { BarChartComponent } from '../Charts/bar-chart/bar-chart.component';






const routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      { path: '', redirectTo: 'listStory', pathMatch: 'full' },
      {
        path: 'createStory',
        component: CreateSComponent
      },
      {
        path: 'listStory',
        component: ListSComponent
      },
      {
        path: 'author',
        component: CreatelistAComponent
      },
      {
        path: 'category',
        component: CreatelistCComponent
      },
      {
        path: 'createCategory',
        component: CreateCComponent
      },
      {
        path: 'createAuthor',
        component: CreateAComponent
      },
      {
        path: 'createChapter',
        component: CreateChapterComponent
      },
      {
        path: 'createChapterforBook/:id',
        component: CreateChapterComponent
      },
      {
        path: 'editStory/:id',
        component: EditSComponent
      },
      {
        path: 'listChapter/:id',
        component: ListChapterComponent,
      },
      {
        path: 'listCategory/:id',
        component: ListChapterComponent,
      },
      {
        path: 'editCategory/:id',
        component: EditCComponent,
      },
      {
        path: 'editAuthor/:id',
        component: EditAComponent,
      },
      {
        path: 'editChapter/:id',
        component: EditChapterComponent,
      },
      {
        path: 'upload',
        component: UploadImageComponent,
      },
      {
        path: 'statistical',
        component: BarChartComponent,
      },

    ]
  }
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes), FormsModule,
    HttpClientModule,
    NgxPaginationModule,
    DataTablesModule,
    FlxUiDatatableModule,
    Ng2SearchPipeModule,ChartsModule,
    CKEditorModule,

    
    
  ],
  providers: [FlxUiDataTable,],
  declarations: [AdminComponent, CreateSComponent, CreatelistAComponent,
    CreatelistCComponent,BarChartComponent,
    CreateCComponent, ListSComponent, CreateAComponent, CreateChapterComponent, ListChapterComponent, EditSComponent, EditChapterComponent, EditCComponent, EditAComponent, UploadImageComponent]
})
export class AdminModule { }
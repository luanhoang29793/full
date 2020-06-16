import { Component, OnInit } from '@angular/core';
import { ChartsModule } from 'ng2-charts';
import { Observable } from 'rxjs';
import { Charts } from 'src/app/model/charts';
import { Router } from '@angular/router';
import{ChartService}from 'src/app/service/chart.service'
@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css']
})
export class BarChartComponent implements OnInit {
  charts: Charts[];
 
  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels = [];
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData = [
    {data:[], label: 'Book '},
  ];
  
  constructor(  
    private router :Router,
    private chartService: ChartService) { }
  ngOnInit() {
    this.reloadData();
    

  }
  reloadData(){
    this.chartService.getAllMonth().subscribe(data => {
      // console.log(data)
      this.charts = data;
      for (let i = 0; i < data.length; i++) {
        this.barChartLabels[i] = data[i].month;
        this.barChartData[0].data[i] = data[i].count;

        console.log(this.barChartLabels,"label")
        console.log(data[i].count)
         }
         console.log(data,"data")
         console.log(this.barChartData[0].data,"barchart data")
    }, error => console.log(error));
  }

}

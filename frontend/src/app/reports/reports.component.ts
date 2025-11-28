import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { DataService } from '../data.service';
import { CommonModule } from '@angular/common';
import * as d3 from 'd3';

@Component({
  selector: 'app-reports',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reports.component.html',
  styleUrl: './reports.component.css'
})
export class ReportsComponent implements OnInit {
  @ViewChild('chart', { static: true }) chartElement!: ElementRef;

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.dataService.getReportsChart().subscribe(data => {
      this.createChart(data);
    });
  }

  createChart(data: any[]) {
    const svg = d3.select(this.chartElement.nativeElement)
      .append('svg')
      .attr('width', 400)
      .attr('height', 300)
      .attr('role', 'img')
      .attr('aria-labelledby', 'chart-title');

    const margin = { top: 20, right: 30, bottom: 40, left: 40 };
    const width = 400 - margin.left - margin.right;
    const height = 300 - margin.top - margin.bottom;

    const g = svg.append('g')
      .attr('transform', `translate(${margin.left},${margin.top})`);

    const x = d3.scaleBand()
      .domain(data.map(d => d.type))
      .range([0, width])
      .padding(0.1);

    const y = d3.scaleLinear()
      .domain([0, d3.max(data, d => d.count)!])
      .range([height, 0]);

    g.append('g')
      .attr('transform', `translate(0,${height})`)
      .call(d3.axisBottom(x));

    g.append('g')
      .call(d3.axisLeft(y));

    g.selectAll('.bar')
      .data(data)
      .enter().append('rect')
      .attr('class', 'bar')
      .attr('x', d => x(d.type)!)
      .attr('y', d => y(d.count))
      .attr('width', x.bandwidth())
      .attr('height', d => height - y(d.count))
      .attr('fill', 'green');
  }
}

import { DataService } from './../../service/data.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, SelectControlValueAccessor, Validators } from '@angular/forms';
import { Cars } from 'src/app/cars';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {

  cars: Cars[] = []

  carnames = ['Audi', 'Alfa Romeo', 'Mercedes', 'BMW', 'Volkswagen', 'Toyota', 'Opel']
  carcolors = ['Black', 'Red', 'White', 'Blue', 'Yellow', 'Grey', 'Green', 'Pink', 'Orange']
  caryears = Array(2021 - (2021 - 50)).fill('').map((v, idx) => (2021 - idx) + '')

  newcar = { name: '', color: '', manYear: '', nr: '' }

  //whether to show the car form or not
  showForm = false

  // update or save state
  save = false
  update = false

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.getCars()
  }

  getCars() {
    this.cars = []
    this.dataService.getCars().subscribe(values => this.setCars(values as Cars[]))
  }

  setCars(values: Cars[]) {
    values.forEach(element => {
      this.cars.push(element)
    });
    console.log(this.cars)
  }

  rendszam = new FormGroup({
    nr: new FormControl('', [Validators.pattern('^([A-Z]{3}-[0-9]{3})$'), Validators.required])
  });

  get nr() {
    return this.rendszam.get('nr');
  }
  
  errors() {
    if(!this.newcar.name || !this.newcar.manYear || !this.newcar.color || this.rendszam.controls.nr.errors) {
      return true;
    }
    return false;
  }

  addNew() {
    this.newcar = {} as Cars
    this.rendszam.controls.nr.setValue('')
    this.update = false
    this.save = true
    this.showForm = true
  }

  // the actual car to modify
  actualCar : Cars = {} as Cars

  updateCar(car: Cars) {
    this.save = false
    this.actualCar = car

    this.newcar.name = car.name
    this.newcar.color = car.color
    this.newcar.manYear = car.manYear
    this.newcar.nr = car.nr
    this.rendszam.controls.nr.setValue(car.nr)

    this.update = true
    this.showForm = true
  }

  // save the new or update the car
  saveOrUpdate() {
    if(this.update) {
      this.newcar.nr = this.rendszam.controls.nr.value
      if ( // only if the name is different, we use PATCH
          this.actualCar.color == this.newcar.color &&
          this.actualCar.manYear == this.newcar.manYear && 
          this.actualCar.nr == this.newcar.nr && 
          this.actualCar.name != this.newcar.name
        ) 
      {
        this.dataService.updateCarName(this.newcar.name, this.actualCar.id).subscribe(response => this.getCars())
      }
      else {
        this.dataService.updateCar(this.newcar, this.actualCar.id).subscribe(response => this.getCars())
      }
      this.update = false
    }

    if(this.save) {
      this.newcar.nr = this.rendszam.controls.nr.value
      this.dataService.addCar(this.newcar).subscribe(response => this.getCars())
    }

    this.save = false
    this.showForm = false;
  }

  deleteCar(id: string) {
    this.dataService.deleteCar(id).subscribe(response => this.getCars())
  }

}

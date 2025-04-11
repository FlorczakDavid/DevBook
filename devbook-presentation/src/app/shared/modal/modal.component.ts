import { Component, EventEmitter, Input, Output } from '@angular/core';
import {FormGroup, FormControl, ReactiveFormsModule} from '@angular/forms';
import axios from 'axios'
import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-modal',
  imports : [CommonModule, ReactiveFormsModule],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})

export class ModalComponent {
  @Output() close = new EventEmitter<void>();
  @Output() shareArticle = new EventEmitter<void>();
  @Input() formGroup: FormGroup = new FormGroup({
    url: new FormControl('')
  });

  closeModal(): void{
    this.close.emit();
  }

  onSubmit(){

    console.log("toto")
    if(this.formGroup.valid){
      console.log("tata")
      this.shareArticle.emit();
    }
  }
  isInvalidAndTouchedOrDirty(control: any): boolean {
    return control.invalid && (control.dirty || control.touched);
  }

  url: string='';

  createValidatorUrl(): ValidatorFn {
    return (control:AbstractControl) : ValidationErrors | null => {
      const value = control.value;
      if(!value) return null;

      const hasUrlFormat = /^(https|http).\/\/(a-zA-Z0-9.){1,253}.(a-zA-Z0-9){2,63}/.test(value);
      const urlValid = hasUrlFormat;
      return !urlValid?{urlStrength:true}:null;
    }
  }

  async send(): Promise<void>{
    this.url;
    console.log("url" + this.url);
    
    //this.createValidatorUrl()

    try{
      const response = await axios.post('http://localhost:8080/article',
      {url: this.url},
    {headers:{'Content-Type':'application/json'}});
    }catch(err:any){
      if(err.response){
      
        console.log("error.response.data.fieldsErrors"+err.response.data.fieldsErrors.url)

        const statusCode = err.response.status;
          if(statusCode >= 400 && statusCode < 500){
            alert(err.response.data.fieldsErrors.label)
          }else if(statusCode >= 500 && statusCode < 600){
            alert('A server error has occurred!')
          }
      }else{
        alert('an unexpected error has occured');
        console.error('an unexpected error has occured', err);
      }
    }
  }

 

}

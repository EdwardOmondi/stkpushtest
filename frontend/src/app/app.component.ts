import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { AppService } from './app.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  stkForm = new FormGroup({
    phonenumber: new FormControl('', [
      Validators.required,
      Validators.pattern('[254][0-9]{11}'),
    ]),
    callbackUrl: new FormControl(''),
  });
  errorMessage: string = '';
  loading: boolean = false;
  successMessage: string = '';

  constructor(private service: AppService) {}

  submitForm() {
    this.loading = true;
    this.service.submitForm(this.stkForm.value).subscribe(async (response) => {
      this.loading = false;
      console.log("response",response);
      console.log("response.CustomerMessage",response.CustomerMessage);

      if (response.ResponseCode !== '0') {
        this.errorMessage = response.errorMessage
          ? response.errorMessage
          : response.CustomerMessage;
        await this.delay(4000);
        this.errorMessage = '';
      } else {
        this.successMessage = response.CustomerMessage;
        await this.delay(4000);
        this.successMessage = '';
      }
    });
  }
  private delay(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
}

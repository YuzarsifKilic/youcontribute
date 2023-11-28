import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../_services/auth.service";
import {ToastrService} from "ngx-toastr";
import {first} from "rxjs";

@Component({
  selector: 'app-github-callback',
  templateUrl: './github-callback.component.html',
  styleUrl: './github-callback.component.css'
})
export class GithubCallbackComponent implements OnInit {

  loading = false;

  constructor(private route: ActivatedRoute,
              private authService: AuthService,
              private router: Router,
              private toastr: ToastrService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.getAccessToken(params["token"])
    })
  }


  private getAccessToken(code: string){
    this.loading = true;
    this.authService.getAccessToken(code)
      .pipe(first())
      .subscribe((resp) => {
        this.loading = true;
        this.toastr.success("Sucessfully logged in, redirecting to home page...", "Success");
        localStorage.setItem("token", resp.accessToken)
        setTimeout(() => {
          this.router.navigate(["home"])
        }, 2000);
      }, error => {
        this.loading = false;
        this.toastr.error(error.error.message, "Error");
      })
  }
}

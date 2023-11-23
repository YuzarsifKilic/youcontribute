import {Component, OnInit} from '@angular/core';
import {ChallengeService} from "../../_services/challenge.service";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-accept',
  templateUrl: './accept.component.html',
  styleUrl: './accept.component.css'
})
export class AcceptComponent implements OnInit{

  loading = false;

  constructor(private challengeService: ChallengeService,
              private route: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.accept(params["id"]);
    })
  }

  private accept(id: number) {
    this.loading = true;
    this.challengeService.accept(id)
      .pipe(first())
      .subscribe((resp) => {
        this.loading = false;
        this.toastr.success("Challenge accepted successfully", "Success");
        setTimeout(() => {
          this.router.navigate(["challenges"])
        })
      }, error =>  {
        this.loading = false;
        this.toastr.error(error.error.message, "Error");
    });
  }
}

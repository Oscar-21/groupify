import React, { Component } from 'react';
import Event from '../../dto/common/Event';
import { apiGet } from '../../util/api';
import SuccessOrErrorResponse from '../../dto/SuccessOrErrorResponse';


const initialEventsState: Event[] = [];
export default class UpcomingEvents extends Component {
  state = {
    events: [],
    currentTravelRadius: 0
  };

  componentDidMount() {
    apiGet<{currentTravelRadius: number, error?: string}>('/songkick/get-travel-radius').then(({ currentTravelRadius, error }) => {
      if (!error) {
        this.setState({ currentTravelRadius });  
      }
    });
  };

  updateTravelRadius = () => {
    apiGet<SuccessOrErrorResponse>(`/songkick/update-travel-radius?radius=${this.state.currentTravelRadius}`)
    .then(response => {
      if (response.success) {
        alert("success");
      } else if (response.error) {
        alert("error");
      }
    });
  };

  render() {
    const { events, currentTravelRadius } = this.state;
    return (
      <div>
        <label htmlFor="travelRadius">Distance willing to travel: </label>
        <input 
          type="text" 
          name="travelRadius" 
          id="travelRadius" 
          value={currentTravelRadius}
          onChange={(e: any) => { 
            const numeric = /^\d+$/
            const emptyString = /^$/
            if (numeric.test(e.target.value) || emptyString.test(e.target.value)) {
              this.setState({currentTravelRadius: e.target.value }) 
            }
          }}
        />
        <button onClick={this.updateTravelRadius}>Update your travel radius</button>
      </div>
    );
  }
}
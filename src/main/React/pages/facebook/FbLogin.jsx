/**
 * Created by adib on 27/03/17.
 */
import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import ChartDisplay from 'components/charts/ChartDisplay'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/charts'

import DatePicker from 'react-datepicker'
import moment from 'moment'
import 'react-datepicker/dist/react-datepicker.css';


class FbLoginCmp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            startDate: moment(),
            endDate: moment()
        };
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    componentDidMount(){
        this.props.loadChartTest()
    }

    getChartData(){
        if(!this.props.chartData){
            console.log(" - return null")
            return []
        }
        console.log(" Mapping state to props =>")
        console.log(JSON.parse(JSON.stringify(this.props.chartData)))

        return JSON.parse(JSON.stringify(this.props.chartData))
    }

    handleChange(date, event) {
        event.preventDefault();
        console.log(date.unix())
        this.setState({startDate: date})
    }

    handleChange2(date, event) {
        event.preventDefault();
        console.log(date.unix())
        this.setState({startDate: date})
        return false;
    }

    findName() {
        this.props.loadChartTest2(this.state.startDate, this.state.endDate)
    }


    render() {
        return  <div>
                    <h1>Chart example</h1>
                    <DatePicker
                        selected={this.state.startDate}
                        onChange={this.handleChange.bind(this)} />
            <br/>
                    <DatePicker
                        selected={this.state.endDate}
                        onChange={this.handleChange2.bind(this)} />
                    <ChartDisplay chartData = {this.getChartData()} />
                </div>
    }
};


function mapStateToProps(state) {
    return {
        needRefresh: state.term.get('needRefresh'),
        chartData: state.chart.get('chartData')
    }
}

export const FbLogin = connect(mapStateToProps, actionCreators)(FbLoginCmp)
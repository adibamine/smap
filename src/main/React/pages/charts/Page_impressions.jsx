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

import { Link } from 'react-router'


class Page_impressionsCmp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            startDate: moment().subtract(7, 'days'),
            endDate: moment()
        };
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    getChartData(){
        if(!this.props.chartData){
            return []
        }

        return JSON.parse(JSON.stringify(this.props.chartData))
    }

    handleChange(date) {
        this.setState({startDate: date})
    }

    handleChange2(date) {
        this.setState({endDate: date})
    }

    findName() {
        this.props.load_page_impressions(this.props.params.metric,this.state.startDate.subtract(1,'days').unix(), this.state.endDate.unix())
    }


    render() {
        return  <div>
            <ul>
                <li>
                    <Link to="insights/charts/page_stories">
                        <i className="pe-7s-ribbon"></i>
                        page stories
                    </Link>
                </li>
                <li>
                    <Link to="insights/charts/page_engaged_users">
                        <i className="pe-7s-ribbon"></i>
                        page engaged users
                    </Link>
                </li>
            </ul>
            <h1>{this.props.params.metric}</h1>
            <DatePicker
                dateFormat="DD/MM/YYYY"
                selected={this.state.startDate}
                selectsStart  startDate={this.state.startDate}
                endDate={this.state.endDate}
                onChange={this.handleChange.bind(this)} />
            <DatePicker
                dateFormat="DD/MM/YYYY"
                selected={this.state.endDate}
                selectsEnd  startDate={this.state.startDate}
                endDate={this.state.endDate}
                onChange={this.handleChange2.bind(this)} />
            <button type="submit" className="btn btn-fill btn-primary" onClick={this.findName.bind(this)}>Go</button>
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

export const Page_impressions = connect(mapStateToProps, actionCreators)(Page_impressionsCmp)